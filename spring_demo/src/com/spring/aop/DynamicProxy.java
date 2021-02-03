package com.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class DynamicProxy {

    public static Object getProxyInstance(Object obj) {

        Myhandler h = new Myhandler(obj);

        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), 
                obj.getClass().getInterfaces(), h);
    }
}

class Myhandler implements InvocationHandler{

    // 进来被代理类
    private Object obj;

    // 初始化赋值被代理类
    public Myhandler(Object obj) {
        this.obj = obj;
    }

    /**
     * 
     * @param proxy 代理类的对象
     * @param method 代理类调用的方法
     * @param args 形参列表
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("增强之前的我是这样的..." + "我的名字是: "+ method.getName() + "我的参数是: "+ Arrays.toString(args));

        // 这个方法的主要作用就是执行代理类的a方法，就会自动执行被代理类的同名a方法
        Object returnVal = method.invoke(obj, args);

        System.out.println("方法执行之后");

        return returnVal;
    }
}
