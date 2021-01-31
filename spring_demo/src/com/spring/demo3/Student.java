package com.spring.demo3;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Student {
    
    private String[] stucourses;
    private List<String> stulist;
    private Map<String, String> stumaps;
    private Set<String> stusets;

    private List<String> hobby;

    public List<String> getHobby() {
        return this.hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    // 对象
    private List<Course> courseList;

    public void setCourseList(List<Course> courseList ) {
        this.courseList = courseList;
    }

    public String[] getStucourses() {
        return this.stucourses;
    }

    public void setStucourses(String[] stucourses) {
        this.stucourses = stucourses;
    }

    public List<String> getStulist() {
        return this.stulist;
    }

    public void setStulist(List<String> stulist) {
        this.stulist = stulist;
    }

    public Map<String,String> getStumaps() {
        return this.stumaps;
    }

    public void setStumaps(Map<String,String> stumaps) {
        this.stumaps = stumaps;
    }

    public Set<String> getStusets() {
        return this.stusets;
    }

    public void setStusets(Set<String> stusets) {
        this.stusets = stusets;
    }

    @Override
    public String toString() {
        return "{" +
            " stucourses='" + getStucourses() + "'" +
            ", stulist='" + getStulist() + "'" +
            ", stumaps='" + getStumaps() + "'" +
            ", stusets='" + getStusets() + "'" +
            ", courseList='" + courseList + "'" +
            ", hobby='" + getHobby() + "'" +
            "}";
    }

    
}
