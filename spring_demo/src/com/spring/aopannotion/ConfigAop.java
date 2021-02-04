package com.spring.aopannotion;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.spring"})
// 替代品 默认是true <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ConfigAop {
    
}
