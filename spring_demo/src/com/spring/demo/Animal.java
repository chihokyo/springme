package com.spring.demo;

public class Animal {
    private String aniname;
    private int aniage;
    private String anicolor;

    public void setAniname(String aniname) {
        this.aniname = aniname;    
    }
    public void setAniage(int aniage) {
        this.aniage = aniage;
    }

    public void setAnicolor(String anicolor) {
        this.anicolor = anicolor;
    }

    public void show() {
        System.out.println("aniname: " + aniname + ", aniage: " + aniage + ", anicolor: " + anicolor);
    }
}
