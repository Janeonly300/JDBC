package com.jianyi.exert;

import com.jianyi.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * @Author 简一
 * @crateTime 2020-05-17 17:17
 */
public class ExertTest3 {

    /*
    根据学生考号好删除该学生
     */
    public static void main(String args[]){
        System.out.println("请输入您的准考证号");
        String str = new Scanner(System.in).next();
        String sql = "delete from examsudent where examCard = ?";
        int update = update(sql, str);
        if(update>0){
            System.out.println("删除成功");
        }else{
            System.out.println("查无此人");
        }
    }

    public static int update(String sql,Object...args){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //获取数据库连接
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);

            //填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            //执行
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtil.closeResource(conn,ps);
        }
        return 0;
    }
}
