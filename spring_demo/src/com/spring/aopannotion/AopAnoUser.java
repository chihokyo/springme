package com.spring.aopannotion;

import org.springframework.stereotype.Component;

/**
 * 被增强类 User 用来验证AOP Aspect注解
 */
@Component
public class AopAnoUser {
    
    public void show() {
        System.out.println("AopAnoUser show()...");
    }
}
