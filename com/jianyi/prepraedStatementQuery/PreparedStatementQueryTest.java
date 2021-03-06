package com.jianyi.prepraedStatementQuery;

import com.jianyi.bean.Customer;
import com.jianyi.util.JDBCUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 针对于不同表的通用操作
 *
 * @Author 简一
 * @crateTime 2020-05-16 03:48
 */
public class PreparedStatementQueryTest {

    @Test
    public void test2(){
        String sql = "select id,name ,email,birth from customer";
        List<Customer> customers = QueryOOP(Customer.class, sql);
        customers.forEach(System.out::println);
    }


    /**
     *
     * 通用查询，支持多条记录查询
     */
    public<T> List<T> QueryOOP(Class<T> clazz, String sql , Object...args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> list = null;
        try {
            //获取数据库连接对象
            conn = JDBCUtil.getConnection();
            //获取数据库操作对象
            ps = conn.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }

            //获取查询结果集
            rs = ps.executeQuery();
            //获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取字段数
            int columnCount = rsmd.getColumnCount();

            //创建集合
            list = new ArrayList<>();
            while(rs.next()){
                T t = clazz.newInstance();
                for( int i =0;i<columnCount;i++){
                    //获取字段值
                    Object value = rs.getObject(i + 1);
                    //获取字段别名
                    String columnLabel = rsmd.getColumnLabel(i+1);

                    //属性赋值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,value);
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(conn,ps,rs);
        }
        return list;

    }


    @Test
    public void test1(){
        //测试查询customer
        String sql = "select id,name,email,birth from customer where id = ?";
        Customer customer = queryAll(Customer.class, sql, 2);
        System.out.println(customer);
    }

    public <T> T queryAll(Class<T> clazz,String sql,Object...args)  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取数据库连接
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);

            for(int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            //获取查询结果集
            rs = ps.executeQuery();

            //获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取字段个数
            int columnCount = rsmd.getColumnCount();
            if(rs.next()){
                T t = clazz.newInstance();
                for(int i=0;i<columnCount;i++){
                    //获取结果集值
                    Object columnValue = rs.getObject(i + 1);
                    //获取结果集的字段名
                    String columnName = rsmd.getColumnLabel(i + 1);

                    //通过反射赋值
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true); //保证结果集可读
                    field.set(t,columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(conn,ps,rs);
        }

        return  null;
    }
}
