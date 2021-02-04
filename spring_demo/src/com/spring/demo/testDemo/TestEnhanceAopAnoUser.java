package com.spring.demo.testDemo;

import com.spring.aopannotion.AopAnoUser;
import com.spring.aopannotion.ConfigAop;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
// import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEnhanceAopAnoUser {

    @Test
    public void testEnhanceAopAnoUser() {
        try (
            // ClassPathXmlApplicationContext context = 
            //     new ClassPathXmlApplicationContext("bean8.xml");

            // 完全使用注解开发
            AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(ConfigAop.class);
        ) {
            AopAnoUser aau = context.getBean("aopAnoUser", AopAnoUser.class);
            aau.show();
        }
    }
}
