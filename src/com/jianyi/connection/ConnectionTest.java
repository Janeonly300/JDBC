package com.jianyi.connection;
import com.mysql.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 连接方式1:
 * @Author 简一
 * @crateTime 2020-05-08 01:00
 */
public class ConnectionTest {
    public static void main(String[] args) throws SQLException, IOException {
        Driver driver = new Driver();

        String url ="jdbc:mysql://localhost:3306/test?useSSL=false"; //协议:子协议://ip地址:端口号/数据库

        Properties info =  new Properties();//配置信息
        Logger.getGlobal().info("数据库连接完成");
        info.load(new FileInputStream("day000 - jdbc_1\\src\\jdbc.properties"));

        Connection connect = driver.connect(url, info);

        System.out.println(connect);
    }
}
