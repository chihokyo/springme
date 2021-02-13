package com.jdbctemp.test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testfindCount() {
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean.xml");
        ) {
            UserService userService = context.getBean("userService",UserService.class);
            
            User user = new User();
            int result = userService.findCount(user);
            System.out.println("user:" + result + "条数据");
        }
    }

    @Test
    public void testfindUserObject() {
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean.xml");
        ) {
            UserService userService = context.getBean("userService",UserService.class);
            int id = 1;
            User user = userService.findUserObject(id);
            System.out.println(user.toString());
            
        }
    }

    @Test
    public void testfindUserList() {
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean.xml");
        ) {
            UserService userService = context.getBean("userService",UserService.class);
            List<User> userList = userService.findUserList();
            for (User user : userList) {
                System.out.println(user);
            }
            
        }
    }

    @Test
    public void testBatchAdd(){
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean.xml");
        ) {
            UserService userService = context.getBean("userService",UserService.class);
            List<Object[]> batchArgs = new ArrayList<>();
            Object[] o1 = {12,"Amy4", "password", "191-0000", "2222"};
            Object[] o2 = {13,"Amy5", "password", "191-0000", "2222"};
            Object[] o3 = {14,"Amy6", "password", "191-0000", "2222"};
            batchArgs.add(o1);
            batchArgs.add(o2);
            batchArgs.add(o3);
            userService.batchAdd(batchArgs);
        }
    }

    @Test
    public void testBatchUpdate(){
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean.xml");
        ) {
            UserService userService = context.getBean("userService",UserService.class);
            List<Object[]> batchArgs = new ArrayList<>();
            Object[] o1 = {"Amy4", "password22", "191-0000", "2222", 12};
            Object[] o2 = {"Amy4", "password33", "191-0000", "2222", 13};
            batchArgs.add(o1);
            batchArgs.add(o2);
            userService.batchUpdate(batchArgs);
        }
    }


    @Test
    public void testBatchDelete(){
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean.xml");
        ) {
            UserService userService = context.getBean("userService",UserService.class);
            List<Object[]> batchArgs = new ArrayList<>();
            Object[] o1 = {9};
            Object[] o2 = {10};
            Object[] o3 = {11};
            batchArgs.add(o1);
            batchArgs.add(o2);
            batchArgs.add(o3);
            userService.batchDelete(batchArgs);
        }
    }
}
