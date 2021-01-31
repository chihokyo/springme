package com.spring.demo.testDemo;

import com.spring.demo1.UserService;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserImpl {

    @Test
    public void testUserImpl() {
        try (
            ClassPathXmlApplicationContext context = 
            new ClassPathXmlApplicationContext("bean1.xml");
        ) {
            UserService us = context.getBean("userService", UserService.class);
            us.add();
        }
    }
}
