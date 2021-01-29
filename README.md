# springme

开始学习Spring 嘻嘻

## 小试牛刀

- 新建一个空项目
- 导入*_jar*下的所有jar包
- src下新建一个package 写一份测试代码

```java
package com.spring.demo;

public class User {
    public void add() {
        System.out.println("add success");
    }
}
```

- 创建 Spring 配置文件 在src同目录下新建一个*bean.xml*文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  			
       <bean id="user" class="com.spring.demo.User"></bean>
</beans>
```

- 写一个包写测试代码

```java
package com.spring.demo.testDemo;

import com.spring.demo.User;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {
    
    @Test
    public void testAdd() {
        try (
            // 1.加载配置文件
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean.xml")
        ) {
            // 2.获取配置创建对象
            User user = context.getBean("user", User.class);
            System.out.println(user); // com.spring.demo.User@4facf68f
            user.add(); // add
        }
    }
}

```

差不多这种配置

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20210130023052.png)

**以上 Over 🎉**

