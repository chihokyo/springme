package com.jdbctemp.test;

import com.jdbctemp.config.TxConfig;
import com.jdbctemp.service.UserTableService;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserTable {
    
    @Test
    public void testAccount() {
        try (
            // xml配置文件
            // ClassPathXmlApplicationContext context = 
            //     new ClassPathXmlApplicationContext("bean.xml");

            // 完全注解
            AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(TxConfig.class);
        ) {
            UserTableService utService = context.getBean("userTableService", UserTableService.class);
            utService.accuntMoney();
        }
    }
}
