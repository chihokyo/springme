package com.spring.aopannotion;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 增强类
 */
@Component
@Aspect
public class EnhanceAopAnoUser {

    // 抽取相同的切入点设置 
    // 不想写这一坨
    // value = "execution(* com.spring.aopannotion.AopAnoUser.show(..))"
    @Pointcut(value = "execution(* com.spring.aopannotion.AopAnoUser.show(..))")
    public void pointCut() {
        
    }
    
    // 前置通知
    // Before 注解表示前置通知
    @Before(value = "pointCut()")
    public void beforeAno() {
        System.out.println("前置通知@Before beforeAno()...");
    }

    // 后置通知(返回通知)
    @AfterReturning(value = "pointCut()")
    public void afterReturningAno() {
        System.out.println("后置通知@AfterReturning afterReturningAno()...");
    }

    // 最终通知
    @After(value = "pointCut()")
    public void afterAno() {
        System.out.println("最终通知@After afterAno()...");
    }

    // 异常通知
    @AfterThrowing(value = "pointCut()")
    public void afterThrowingAno() {
        System.out.println("异常通知@AfterThrowing afterThrowingAno()...");
    }
    // 环绕通知
    @Around(value = "pointCut()")
    public void aroundAno(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("环绕之前....");

        //  相当于执行需要增强的方法
        proceedingJoinPoint.proceed();

        System.out.println("环绕之后....");
    }
}
