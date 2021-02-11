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
            
            // 添加
            User user = new User();
            user.setId(9);
            user.setName("Amy2");
            user.setPassword("password");
            user.setAddress("151-0053 ");
            user.setPhone("111111");
            userService.addUser(user);

            
        }
    }

    @Test
    public void testupdateUser(){
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean.xml");
        ) {
            UserService userService = context.getBean("userService",UserService.class);
            
           // 更新
           User user = new User();
           user.setId(9);
           user.setName("Amy888");
           user.setPassword("password");
           user.setAddress("151-0053 ");
           user.setPhone("2222");
           userService.updateUser(user);
        }
    }

    @Test
    public void testdeleteUser(){
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean.xml");
        ) {
            UserService userService = context.getBean("userService",UserService.class);
            
           // 删除
           int id = 9;
           userService.deleteUser(id);
        }
    }
}
