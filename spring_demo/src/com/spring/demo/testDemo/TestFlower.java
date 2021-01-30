package com.spring.demo.testDemo;

import com.spring.demo.Flower;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestFlower {
    @Test
    public void testFlower() {
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean.xml");
        ) {
            Flower flower = context.getBean("flower", Flower.class);
            System.out.println(flower); // { smell='Good!!', price='999'}
            flower.show(); // { smell='Good!!', price='999'}
        }
    }
}
