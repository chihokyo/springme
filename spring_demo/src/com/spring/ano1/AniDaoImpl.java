package com.spring.ano1;

import org.springframework.stereotype.Repository;

// 不写这个value 就是默认首字母小写那种
@Repository(value = "aaaaa")
public class AniDaoImpl implements AniDao  {
    @Override
    public void show() {
        System.out.println("AniDaoImpl show...");
    }
}
