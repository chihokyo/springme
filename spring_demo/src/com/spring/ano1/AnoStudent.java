package com.spring.ano1;

import org.springframework.stereotype.Component;

// 其他三个也可以写Component(value="anoStudent") 默认是类名的首字母小写
@Component
public class AnoStudent {

    public void show() {
        System.out.println("AnoStudent show()...");
    }
}
