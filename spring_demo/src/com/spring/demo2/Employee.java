package com.spring.demo2;

/**
 * 员工类
 */
public class Employee {

    private String ename;
    private String gender;
    private Depart dept;

    public String getEname() {
        return this.ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Depart getDept() {
        return this.dept;
    }

    public void setDept(Depart dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "{" +
            " ename='" + getEname() + "'" +
            ", gender='" + getGender() + "'" +
            ", dept='" + getDept() + "'" +
            "}";
    }

}
