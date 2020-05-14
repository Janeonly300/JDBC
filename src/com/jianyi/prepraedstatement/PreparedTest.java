package com.jianyi.prepraedstatement;

import com.jianyi.util.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @Author 简一
 * @crateTime 2020-05-10 23:52
 */
public class PreparedTest {
    @Test
    public void test(){
        String sql = "insert into customer(name,email) values(?,?)";
        PreparedTest.update(sql,"谢广坤","xie广坤@123.com");
    }

    public static void update(String sql,Object...args){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1. 获取数据库连接
            conn = JDBCUtil.getConnection();

            //2. 获取操作对象
            ps = conn.prepareStatement(sql);

            //3. 填充占位符
            for (int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            //4. 执行操作
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.关闭流
            JDBCUtil.closeResource(conn,ps);
        }



    }
}
