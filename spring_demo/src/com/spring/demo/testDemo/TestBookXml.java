package com.spring.demo.testDemo;

import com.spring.aopxml.BookXml;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBookXml {
    
    @Test
    public void testBookXml() {
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean9.xml");
        ) {
            BookXml bx = context.getBean("book", BookXml.class);
            bx.buy();
            // beforeBuyBook...
            // buy Book...
        }
    }
}
