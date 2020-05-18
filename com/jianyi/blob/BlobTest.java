package com.jianyi.blob;

import com.jianyi.bean.Customer;
import com.jianyi.util.JDBCUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author 简一
 * @crateTime 2020-05-17 23:54
 */
public class BlobTest {

    //向表数据当中添加BLOB类型数据
    @Test
    public void test(){
        Connection conn = null;
        PreparedStatement ps = null;
        InputStream is = null;
        try {
            conn = JDBCUtil.getConnection();

            String sql = "insert into customer(name,photo)values(?,?)";
            ps = conn.prepareStatement(sql);

            //读取流文件
            is = ClassLoader.getSystemClassLoader().getResourceAsStream("preview.jpg");

            //填充占位符
            ps.setObject(1,"人");
            ps.setBlob(2,is);

            //执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if(is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            JDBCUtil.closeResource(conn,ps);
        }
    }

    /*
    读取数据库文件到本地
     */
    @Test
    public void test1() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream binaryStream = null;
        FileOutputStream fos = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select id,name,photo from customer where id = ?";
            ps = conn.prepareStatement(sql);

            //填充占位符
            ps.setInt(1, 14);

            //获取结果集
            rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Customer customer = new Customer();
                customer.setId(id);
                customer.setName(name);

                Blob blob = rs.getBlob(3);
                //获取二进制流
                binaryStream = blob.getBinaryStream();
                fos = new FileOutputStream(new File("亚索.jpg"));
                int len;
                byte[] buffer = new byte[1024];
                //将图片保存到本地
                while ((len = binaryStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                binaryStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //关闭流
            JDBCUtil.closeResource(conn, ps, rs);
        }


    }
}
