package com.spring.demo.testDemo;

import com.spring.demo3.Course;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMyBean {

    @Test
    public void testMybean() {
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean4.xml");
        ) {
            // 普通Bean
            // MyBean mb = context.getBean("myBean", MyBean.class);

            // 定义对象和返回对象不一致的Bean 
            // xml定义的是  <bean id="myBean" class="com.spring.demo4.MyBean"></bean>
            // 返回的是 Course
            Course course = context.getBean("myBean", Course.class);
            System.out.println(course); // com.spring.demo3.Course@41fbdac4
        }    
    }
}
