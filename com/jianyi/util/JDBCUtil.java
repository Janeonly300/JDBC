package com.jianyi.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库连接 关闭
 * @Author 简一
 * @crateTime 2020-05-10 23:44
 */
public class JDBCUtil {

    /**
     *
     * @return 数据库连接对象
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        //加载流
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties pro = new Properties();
        pro.load(is);

        String url = pro.getProperty("url");
        String user = pro.getProperty("user");
        String password = pro.getProperty("password");
        String driverClass = pro.getProperty("driverClass");

        //加载驱动
        Class.forName(driverClass);

        //获取连接
        return DriverManager.getConnection(url,user,password);
    }

    /**
     * 数据库关闭操作
     * @param conn
     * @param ps
     */
    public static void closeResource(Connection conn, Statement ps){
        if(conn!= null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResource(Connection conn, Statement ps, ResultSet rs){
        if(conn!= null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
