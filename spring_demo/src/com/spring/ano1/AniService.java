package com.spring.ano1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// @Component(value="aniService")
@Service
public class AniService {
    
    @Value(value="1234ab")
    private String name;

    // 不需要set方法
    // Autowired 属性类型
    @Autowired
    // @Qualifier(value = "aaaa") 不写这个value 就是默认首字母小写那种
    @Qualifier(value = "aaaaa")
    private AniDao aniDao;

    public void add() {
        System.out.println("AniService add," + name);
        aniDao.show();
    }

}
