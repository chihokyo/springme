package com.spring.demo4;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanLife  implements BeanPostProcessor {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("第二步 调用 set 方法设置属性值 setName(String name)");
    }

    public BeanLife() {
        System.out.println("第一步 执行无参数构造创建 bean 实例 BeanLife()");
    }
    
    public void initMethod() {
        System.out.println("第三步 执行初始化的方法 initMethod()");
    }
    
    public void destoryMethod(){
        System.out.println("第五步 执行销毁的方法destoryMethod()");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("在初始化之前执行的方法 Before...");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("在初始化之后执行的方法 After...");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
