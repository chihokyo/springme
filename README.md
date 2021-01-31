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

## IoC

IoC = Inversion of Cotnrol

看国内的很多老师讲了这么多，然后看了外网的一张图就懂了。就是本来BC都要依赖A才能创建的Class。倒过来，A反而需要B和C了。这就是**控制反转**。

- IoC目的 → 为了降低耦合度（各个模块之间依赖的程度）
- 底层实现原理  → **xml解析 + 工厂模式 + 反射**
- 进化过程 ↓

### 进化过程

1. 原始方式 **耦合度太高了**

```java
class A{
  public add(){}
}
class B{ 
  // 如果B想用A的方法的话那么就要在内部new一个
  A a = new A();
  a.add();
}
```

2. 工厂模式 搞了一个工厂 **专门来new对象**

这样直接避免了 A和B的直接碰撞 但是这种方式也有问题，就是工厂Factory本身和A和B都有关联了。实际上，类和类，对象和对象之间是要减少联系降到最低，并不是完全没有。

```java
class A{
  public add(){}
}
class B{ 
  // 都搞出来工厂了 就不用new A类
	A a = Factory.getA();
  a.add();
}
// 一个工厂类，专业生产A对象
class Factory{
  public static A getA{
    return new A();
  }
}
```

3. IoC是如何解耦的过程。

- xml配置文件 → 创建对象
- 有service类和dao类，创建第三方工厂类。
- 在这个第三方内部使用小试牛刀里的 **a xml解析 b 反射创建对象 c 和往常一样搞。**

为什么能够最低解耦合，就是如果以后对象改变，比如路径，还有名字啥改变。只需要修改xml

`<bean id="user" class="com.spring.demo.User"></bean>` 就可以。

### IoC接口体系原理

**<u>IoC思想基于容器，容器底层本质就是对象工厂。</u>**

感觉IoC接口太多了。主要实现就是根据这俩接口实现的。

`BeanFactory` 是Spring内部实现接口。加载配置文件并不会创建对象，获取or使用才会。

`ApplicationContext` 是`BeanFactory`的子接口。提供更多的功能。加载配置文件之后对象就创建了。

### Bean管理

#### **什么是Bean管理？**

Bean 管理指的是两个操作

> - Spring 创建对象
> - Spirng 注入属性

上面演示了，对象的创建。

```java
public class User {
    private String userName; // 平常用set get Spring可以Bean管理
    public void add() {
        System.out.println("add success");
    }
}
```

#### **IoC操作Bean有哪些方式？**

基于xml方式创建对象

```java
<bean id="user" class="com.spring.demo.User"></bean>
  
(1)在 spring 配置文件中，使用 bean 标签，标签里面添加对应属性，就可以实现对象创建 
(2)在 bean 标签有很多属性，介绍常用的属性
* id 属性:唯一标识
* class 属性:类全路径(包类路径) 
* name属性: 早期属性，现在不用了。都用id
(3)创建对象时候，默认也是执行无参数构造方法完成对象创建
  如果你的类里没有无参构造器的话，就会报错。

```

基于xml方式注入属性

**DI** 依赖注入 本质就是注入属性 DI是IoC的一种表现形式。

以前有哪些属性注入的呢 set方法注入，构造器有参注入

```java
// 普通set方法注入
Class Animal {
  private String name;
  public void setName(String name){
    this.name = name;
  }
}
// 配置xml文件set方法注入
<!-- 配置 Animal 对象创建 -->
  <!-- 使用property完成属性注入
  k-v形式 name 属性名称 value 属性值 -->
<bean id="animal" class="com.spring.demo.Animal">
    <property name="aniname" value="mao"></property>
    <property name="aniage" value="100"></property>
</bean>
// 配置xml文件set方法注入 测试
 @Test
public void testAnimal() {
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean.xml");
        ) {
            Animal animal = context.getBean("animal", Animal.class);
            System.out.println(animal); // com.spring.demo.Animal@689604d9
            animal.show(); // aniname: mao, aniage: 100
        }
}

public class Animal {
    private String aniname;
    private int aniage;

    public void setAniname(String aniname) {
        this.aniname = aniname;    
    }
    public void setAniage(int aniage) {
        this.aniage = aniage;
    }

    public void show() {
        System.out.println("aniname: " + aniname + ", aniage: " + aniage);
    }
}
```

```xml
<!-- 配置 Flower 对象创建 -->
<!-- 2. 有参数注入属性 -->
<!-- 使用 constructor-arg 标签 完成属性注入
        k-v形式 name 属性名称 value 属性值 -->
<bean id="flower" class="com.spring.demo.Flower">
  <!-- 通过名称 -->
  <constructor-arg name="smell" value="Good!!"></constructor-arg>
  <constructor-arg name="price" value="999"></constructor-arg>
  <!-- 通过索引值 -->
  <!-- <constructor-arg index="0" value="Bad!!"></constructor-arg>
       <constructor-arg index="1" value="5"></constructor-arg> -->
</bean>
```

p名称空间注入，就是为了简化xml配置。

- 添加p名称空间在配置文件

`xmlns:p="http://www.springframework.org/schema/p"`

- 进行属性注入，在bean标签进行操作。底层还是set方法注入。

`<bean id="flower" class="com.spring.demo.Flower" p:smell="Yeah!" p:price="99">`

#### 其他类型

#####  **字面量**

- null `<property name="anicolor"><null></null></property>`
- 特殊符号 方法1 转义 方法2 CDATA

比如上面的特殊符号举例

```java
<!-- <property name="anicolor" value="<红色>"></property> 错误的 -->
  
使用转义符号
<property name="anicolor" value="&lt;红色&gt;"></property> 
  
使用CDATA <![[]]> 在第1个中括号里写上CDATA <![CDATA[你想写的内容]]>
<property name="anicolor">
	<value><![CDATA[<红色>]]></value>
</property>
```

##### **注入属性 外部bean**

注意主要其实是通过了 

```xml
<property name="userDao" ref="userDaoImpl"></property>
<bean id="userDaoImpl" class="com.spring.demo1.UserDaoImpl">
ref连接了id和userDao
```



1个接口*UserDao*

1个实现类*UserDaoImpl*

1个普通逻辑类*UserService*

1个测试类*TestUserImpl*

1个xml配置文件

以上按照顺序是这样写的

```java
// 1个接口UserDao
interface UserDao {
    public void update();
}
// 1个实现类UserDaoImpl
public class UserDaoImpl implements UserDao {
    @Override
    public void update() {
        System.out.println("UserDaoImpl update....");
    }
}
// 1个普通逻辑类UserService
public class UserService {

    // UserService内部里使用外部的类
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add() {
        System.out.println("UserService add....");
        userDao.update();
    }
}
// 1个测试类TestUserImpl
public class TestUserImpl {

    @Test
    public void testUserImpl() {
        try (
            ClassPathXmlApplicationContext context = 
            new ClassPathXmlApplicationContext("bean1.xml");
        ) {
            UserService us = context.getBean("userService", UserService.class);
            us.add();
        }
    }
}
// 1个xml配置文件
<bean id="userService" class="com.spring.demo1.UserService">
  	<!-- 在Service里注入userDao的对象 -->
  	<!-- name 属性:类里面属性名称 -->
    <!-- ref 属性:创建 userDao 对象 bean 标签 id 值 -->
      <property name="userDao" ref="userDaoImpl"></property>
      </bean>
     <!-- 这里的对象不是Dao而是Impl 因为接口没有对象 -->
      <bean id="userDaoImpl" class="com.spring.demo1.UserDaoImpl"></bean>
```

