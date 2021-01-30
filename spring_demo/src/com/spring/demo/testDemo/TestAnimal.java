package com.spring.demo.testDemo;

import com.spring.demo.Animal;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnimal {

    @Test
    public void testAnimal() {
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean.xml");
        ) {
            Animal animal = context.getBean("animal", Animal.class);
            System.out.println(animal); // com.spring.demo.Animal@689604d9
            animal.show(); // aniname: mao, aniage: 100
        }
    }
}
