package com.jianyi.exert;

import com.jianyi.bean.Students;
import com.jianyi.util.JDBCUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

/**
 * @Author 简一
 * @crateTime 2020-05-17 16:58
 */
public class ExertTest2 {

    /*
    根据准考证号，或者身份证号，查询学生基本信息
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请选择您要输入的类型:");
        System.out.println("a.身份证号");
        System.out.println("b.准考证号");
        String type = sc.next();
        if(type.equalsIgnoreCase("a")){
            System.out.println("请输入身份证号:");
            String str = sc.next();
            String sql = "select `type` `type`,idCard idCard,examCard examCard,studentName name,location location,grade grade from examsudent where idCard= ?";
            Students students = QueryStudent(Students.class, sql, str);
            if(students!=null){
                System.out.println(students);
            }else{
                System.out.println("查无此人");
            }

        }else if(type.equalsIgnoreCase("b")){
            System.out.println("请输入准考证号:");
            String str = sc.next();
            String sql = "select `type` `type`,idCard idCard,examCard examCard,studentName name,location location,grade grade from examsudent where examCard= ?";
            Students students = QueryStudent(Students.class, sql, str);
            if(students!=null){
                System.out.println(students);
            }else{
                System.out.println("查无此人");
            }

        }else{
            System.out.println("输入类型有误");
        }
    }


    public static<T> T QueryStudent(Class<T> clazz,String sql,Object...args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取数据库连接
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);

            //填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }

            //获取查询结果集
            rs = ps.executeQuery();
            //获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            if (rs.next()){
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //获取字段值
                    Object value = rs.getObject(i + 1);
                    //获取字段别名
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给属性赋值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);//保证无障碍访问属性
                    field.set(t,value);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(conn,ps,rs);
        }

        return null;
    }
}
