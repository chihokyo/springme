package com.spring.demo.testDemo;

import com.spring.aop.DynamicProxy;
import com.spring.aop.DynamicRobot;
import com.spring.aop.DynamicRobotImpl;

import org.junit.Test;

public class TestDynamicRobot {

   @Test
   public void testDynamicRobot() {

        // 新建一个被代理类的实例
        DynamicRobotImpl dr = new DynamicRobotImpl();
        // 动态创建一个代理类
        DynamicRobot proxyDr = (DynamicRobot) DynamicProxy.getProxyInstance(dr);
        // 执行方法
        String result = proxyDr.getEnergy();
        System.out.println(result);
        proxyDr.work("365 day");
   }
}
