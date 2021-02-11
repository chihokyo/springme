package com.jdbctemp.test;

import com.jdbctemp.entity.User;
import com.jdbctemp.service.UserService;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {
    
    @Test
    public void testAddUser(){
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean.xml");
        ) {
            UserService userService = context.getBean("userService",UserService.class);
            User user = new User();
            user.setId(9);
            user.setName("Amy2");
            user.setPassword("password");
            user.setAddress("151-0053 ");
            user.setPhone("111111");
            userService.addUser(user);
        }
    }
}
