package com.jdbctemp.test;

import com.jdbctemp.service.UserTableService;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserTable {
    
    @Test
    public void testAccount() {
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean.xml");
        ) {
            UserTableService utService = context.getBean("userTableService", UserTableService.class);
            utService.accuntMoney();
        }
    }
}
