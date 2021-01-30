package com.spring.demo;

public class Animal {
    private String aniname;
    private int aniage;

    public void setAniname(String aniname) {
        this.aniname = aniname;    
    }
    public void setAniage(int aniage) {
        this.aniage = aniage;
    }

    public void show() {
        System.out.println("aniname: " + aniname + ", aniage: " + aniage);
    }
}
