package com.jianyi.prepraedStatementQuery;

import com.jianyi.bean.Order;
import com.jianyi.util.JDBCUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @Author 简一
 * @crateTime 2020-05-14 22:39
 */
public class QueryFroOrderTest {

    @Test
    public void test2(){
        String sql = "select Order_id id,Order_name name from `Order` where Order_id =?";
        Order order = queryOrder(sql, 2);
        System.out.println(order);
    }

    //针对于Order表数据的通用查询操作
    public Order queryOrder(String sql,Object...args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取数据库连接
            conn = JDBCUtil.getConnection();

            ps = conn.prepareStatement(sql);
            //填充占位符
            for(int i =0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }

            //获取查询结果集
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            //获取字段数量
            int columnCount = rsmd.getColumnCount();
            if(rs.next()){
                Order order = new Order();
                for(int i=0;i<columnCount;i++){
                    //获取字段值
                    Object object = rs.getObject(i + 1);

                    //获取字段名
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //填充属性
                    Field field = Order.class.getDeclaredField(columnLabel);
                    field.setAccessible(true); //保证属性可见
                    field.set(order,object);
                }
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(conn,ps,rs);
        }

        return null;
    }


    //针对于Order表数据的查询操作
    @Test
    public void test1(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取数据库连接
            conn = JDBCUtil.getConnection();

            String sql = "select order_id,order_name,order_date from `order` where order_id = ?";
            ps = conn.prepareStatement(sql);

            //获取查询结果集
            ps.setObject(1,2);
            rs = ps.executeQuery();

            if(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Date date = rs.getDate(3);

                Order order = new Order(id,name,date);
                System.out.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtil.closeResource(conn,ps,rs);
        }



    }
}
