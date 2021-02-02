package com.spring.demo.testDemo;

import com.spring.ano1.AniService;
import com.spring.ano1.SpringConfig;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
// import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAniService {

    @Test
    public void testAniService() {
        try (
            // ClassPathXmlApplicationContext context = 
            //     new ClassPathXmlApplicationContext("bean7.xml");

            // 完全使用注解开发
            AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(SpringConfig.class);

        ) {
            AniService as = context.getBean("aniService", AniService.class);
            as.add();
        }
    }
}
