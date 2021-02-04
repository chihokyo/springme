package com.spring.aopannotion;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class EnhanceAopAnoUserMore {
    
    @Before(value = "execution(* com.spring.aopannotion.AopAnoUser.show(..))")
    public void beforeAnoMore() {
        System.out.println("EnhanceAopAnoUserMore @Before beforeAnoMore()");
    }
}
