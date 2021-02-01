package com.spring.demo.testDemo;

import com.spring.demo3.Student;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestStudent {
    
    @Test
    public void testStudent() {
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean3.xml");
        ) {
            Student s = context.getBean("student", Student.class);
            System.out.println(s);
            // { stucourses='[Ljava.lang.String;@4135c3b', stulist='["1", "2", "3"]'
            // , stumaps='{java=JAVA, js=JS, python=python}', stusets='[Mysql, MongoDB]'}

            Student s1 = context.getBean("student", Student.class);
            Student s2 = context.getBean("student", Student.class);
            System.out.println(s1 == s2); // true
        }
    }
}
