package com.jianyi.blob;

import com.jianyi.util.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author 简一
 * @crateTime 2020-05-18 00:11
 */
public class InsertTest {

    /*
    数据的批量操作
     */
    @Test
    public void test1(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "Insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);

            long start = System.currentTimeMillis();
            //填充占位符 并且执行
            for (int i = 0; i < 20000; i++) {
                ps.setObject(1,"name_"+i);

                ps.execute();
            }
            long end = System.currentTimeMillis();

            System.out.println(end-start+"ms");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtil.closeResource(conn,ps);
        }
    }

    /*
    数据库批量操作的优化
     */
    @Test
    public void test2(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            //取消事务自定提交
            conn.setAutoCommit(false);

            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);

            for(int i=0;i<1000000;i++){
                //填充占位符
                ps.setObject(1,"name_"+i);

                //攒sql
                ps.addBatch();
                if(i%5000==0){
                    //执行
                    ps.executeBatch();

                    //释放
                    ps.clearBatch();
                }
            }
            //提交数据
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        //关闭流
        JDBCUtil.closeResource(conn,ps);

    }


}
