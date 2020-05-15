package com.jianyi.prepraedStatementQuery;

import com.jianyi.bean.Customer;
import com.jianyi.util.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author 简一
 * @crateTime 2020-05-13 17:04
 */
public class CustomerForQueryTest {

    /**
     * 针对于Customer查询的通用操作
     */
    public void queryCustomer(){

    }


    @Test
    public void test() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取数据库连接
            conn = JDBCUtil.getConnection();

            //获取数据库操作对象
            String sql = "select id,name,email,birth from customer where id = ?";
            ps = conn.prepareStatement(sql);

            //填充占位符
            ps.setObject(1,2);

            //返回结果集
            rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                Date birth = rs.getDate(4);

                Customer cos = new Customer(id,name,email,birth);
                System.out.println(cos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtil.closeResource(conn,ps,rs);
        }
    }
}
