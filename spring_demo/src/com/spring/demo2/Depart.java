package com.spring.demo2;

/**
 * 部门类
 */
public class Depart {

    private String dname;

    public String getDname() {
        return this.dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "{" + " dname='" + getDname() + "'" + "}";
    }

}
