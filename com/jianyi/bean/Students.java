package com.jianyi.bean;

/**
 * @Author 简一
 * @crateTime 2020-05-17 16:39
 */
public class Students {
    private int id; //流水号
    private int type; //考试类型
    private String idCard; //身份证号码
    private String examCard; //准考证号
    private String name;  //姓名
    private String location; //位置
    private int grade; //成绩

    @Override
    public String toString() {
        return
                "流水号:" + id +
                "\n, 四级/六级:" + type +
                "\n，身份证号:" + idCard + '\'' +
                "\n, 准考证号:'" + examCard + '\'' +
                "\n, 学生姓名:'" + name + '\'' +
                "\n, 家乡:'" + location + '\'' +
                "\n, 成绩:" + grade +
                "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getExamCard() {
        return examCard;
    }

    public void setExamCard(String examCard) {
        this.examCard = examCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Students(int id, int type, String idCard, String examCard, String name, String location, int grade) {
        this.id = id;
        this.type = type;
        this.idCard = idCard;
        this.examCard = examCard;
        this.name = name;
        this.location = location;
        this.grade = grade;
    }

    public Students() {
    }
}
