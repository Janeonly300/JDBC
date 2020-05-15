package com.jianyi.prepraedstatement;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 *
 * 使用PreparedStatement 做增删改；
 * @Author 简一
 * @crateTime 2020-05-10 01:56
 */
public class PreparedStatementCRUD {

    @Test
    public void test1() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

            //加载配置信息
            Properties pros = new Properties();
            pros.load(is);

            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String driverClass = pros.getProperty("driverClass");

            //加载驱动
            Class.forName(driverClass);

            //获取数据库连接
            conn = DriverManager.getConnection(url,user,password);

            //获取操作对象
            String sql = "insert into customer(name,email,birth)values(?,?,?)"; //?为占位符
            ps = conn.prepareStatement(sql);
            ps.setString(1,"iKUN");
            ps.setString(2,"iKun@Gmail.com");

            //创建生日
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date =sdf.parse("2002-1-13");
            ps.setDate(3,new java.sql.Date(date.getTime())); //

            //执行操作
            ps.execute();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                if(ps!=null)
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
