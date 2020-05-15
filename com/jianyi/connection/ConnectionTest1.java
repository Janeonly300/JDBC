package com.jianyi.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author 简一
 * @crateTime 2020-05-08 23:35
 */
public class ConnectionTest1 {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        InputStream is = ConnectionTest1.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties pros = new Properties();
        pros.load(is);

        String user =pros.getProperty("user");
        String password= pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass=pros.getProperty("driverClass");

        //加载 驱动
        Class.forName(driverClass);

        //获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);

    }
}
