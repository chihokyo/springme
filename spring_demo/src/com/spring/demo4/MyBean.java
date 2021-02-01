package com.spring.demo4;

import com.spring.demo3.Course;

import org.springframework.beans.factory.FactoryBean;

public class MyBean implements FactoryBean<Course> {

    // 定义的对象和返回的对象不一致，就是这个方法决定的
    // 定义返回Bean 默认是Object 所以使用泛型返回你想要的Bean
    @Override
    public Course getObject() throws Exception {
        // 这里应该用反射的 为了方便 直接new
        Course course = new Course();
        course.setCname("Japanese");
        return course;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

}
