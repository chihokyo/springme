package com.spring.demo.testDemo;

import com.spring.demo2.Employee;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEmployee {

    @Test
    public void testEmployee() {
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean2.xml");
        ) {
            Employee em = context.getBean("employee", Employee.class);
            System.out.println(em);
        }
    }
}
