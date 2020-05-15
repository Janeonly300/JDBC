package com.jianyi.bean;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * ORM设计模式，
 *  每个对象对应一条数据
 *  每个属性，对应一个字段
 *
 * @Author 简一
 * @crateTime 2020-05-13 17:04
 */
public class Customer {
    private int id;
    private String name;
    private String email;
    private Date birth;

    public Customer(int id, String name, String email, Date birth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(name, customer.name) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(birth, customer.birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, birth);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
