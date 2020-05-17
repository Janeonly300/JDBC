package com.jianyi.exert;

import com.jianyi.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * @Author 简一
 * @crateTime 2020-05-17 16:43
 */
public class ExertTest1 {

    //练习1：实现插入一个新的学生
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入考生信息:");
        System.out.println("四六级：");
        int type = sc.nextInt();
        System.out.println("身份证号");
        String idCard = sc.next();
        System.out.println("准考证号");
        String examCard = sc.next();
        System.out.println("学生姓名");
        String name = sc.next();
        System.out.println("地址");
        String location = sc.next();
        System.out.println("成绩:");
        int grade = sc.nextInt();

        String sql = "insert into examsudent(`type`,idCard,examCard,studentName,location,grade) values" +
                "(?,?,?,?,?,?) ";
        int update = update(sql, type, idCard, examCard, name, location, grade);
        if(update>0){
            System.out.println("录入成功");
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
