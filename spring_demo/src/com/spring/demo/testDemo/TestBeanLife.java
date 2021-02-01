package com.spring.demo.testDemo;

import com.spring.demo4.BeanLife;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeanLife {

    @Test
   public void testBeanLife() {
       try (
        ClassPathXmlApplicationContext context = 
            new ClassPathXmlApplicationContext("bean4.xml");
       ) {
           BeanLife bl = context.getBean("beanLife", BeanLife.class);
           System.out.println("第四步 获取创建 bean 实例对象");
           System.out.println(bl);
       }
   }

    // 第二步 调用 set 方法设置属性值 setName(String name)
    // 第三步 执行初始化的方法 initMethod()
    // 第四步 获取创建 bean 实例对象
    // com.spring.demo4.BeanLife@131774fe
    // 第五步 执行销毁的方法destoryMethod()
}
