package com.spring.demo.testDemo;

import com.spring.ano1.AnoStudent;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnoStudent {

    @Test
    public void testAnoStudent() {
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean6.xml");
        ) {
            AnoStudent as = context.getBean("anoStudent", AnoStudent.class);
            System.out.println(as);
            as.show();
        }
    }
}
