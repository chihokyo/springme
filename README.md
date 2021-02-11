# springme

开始学习Spring 嘻嘻

## 1. 小试牛刀

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

## 2. IoC

IoC = Inversion of Cotnrol

看国内的很多老师讲了这么多，然后看了外网的一张图就懂了。就是本来BC都要依赖A才能创建的Class。倒过来，A反而需要B和C了。这就是**控制反转**。

- IoC目的 → 为了降低耦合度（各个模块之间依赖的程度）
- 底层实现原理  → **xml解析 + 工厂模式 + 反射**
- 进化过程 ↓

### 2-1 进化过程

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

### 2-2 IoC接口体系原理

**<u>IoC思想基于容器，容器底层本质就是对象工厂。</u>**

感觉IoC接口太多了。主要实现就是根据这俩接口实现的。

`BeanFactory` 是Spring内部实现接口。加载配置文件并不会创建对象，获取or使用才会。

`ApplicationContext` 是`BeanFactory`的子接口。提供更多的功能。加载配置文件之后对象就创建了。

### 2-3 xml的Bean管理

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

## **3. IoC操作Bean之「XML」**

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

**DI** <u>Dependency Injection</u> 依赖注入 本质就是注入属性 DI是IoC的一种表现形式。

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

### 3-1 注入其他其他类型

####  **字面量**

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

### 3-2 **注入属性 外部bean**

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

### 3-3 **注入属性 内部bean**

在两个类中有包含关系的时候会用到内部bean注入。

1个类*Employee* 多个员工从属于一个部分

1个类*Depart* 

1个测试类*TestUserImpl*

1个xml配置文件

以上按照顺序是这样写的

```java
// 配置文件
<bean id="employee" class="com.spring.demo2.Employee">
  <property name="ename" value="Tom"></property>
  <property name="gender" value="女"></property>
  <property name="dept">
  	<bean id="dept" class="com.spring.demo2.Depart">
    	<property name="dname" value="HR"></property>
    </bean>
    </property>
</bean>
// 测试类
public class TestEmployee {

    @Test
    public void testEmployee() {
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean2.xml");
        ) {
            Employee em = context.getBean("employee", Employee.class);
            System.out.println(em);
        }
    }
}
// 2个普通类
/**
 * 部门类
 */
public class Depart {

    private String dname;

    public String getDname() {
        return this.dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "{" + " dname='" + getDname() + "'" + "}";
    }

}
/**
 * 员工类
 */
public class Employee {

    private String ename;
    private String gender;
    private Depart dept;

    public String getEname() {
        return this.ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Depart getDept() {
        return this.dept;
    }

    public void setDept(Depart dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "{" +
            " ename='" + getEname() + "'" +
            ", gender='" + getGender() + "'" +
            ", dept='" + getDept() + "'" +
            "}";
    }

}

```

#### **注入属性 内部bean-级联赋值**

方法1 主要其实就是xml文件和上面对比一下就知道

```xml
<bean id="employee" class="com.spring.demo2.Employee">
  <property name="ename" value="Tom"></property>
  <property name="gender" value="女"></property>
  <!-- 以前的写法 -->
  <!-- <property name="dept">
      <bean id="dept" class="com.spring.demo2.Depart">
      	<property name="dname" value="HR"></property>
      </bean>
   </property> -->

  <!-- 级联赋值 -->
  <property name="dept" ref="dept"></property>
</bean>
<bean id="dept" class="com.spring.demo2.Depart">
  <property name="dname" value="Manager"></property>
</bean>
```

方法2 也只是修改了xml 但需要对象有get方法

```xml
// 需要有属性的get方法之后
<bean id="employee" class="com.spring.demo2.Employee">
  <property name="ename" value="Tom"></property>
  <property name="gender" value="女"></property>
  <property name="dept" ref="dept"></property>
  <property name="dept.dname" value="CEO"></property>
</bean>
<bean id="dept" class="com.spring.demo2.Depart">
  <property name="dname" value="Manager"></property>
</bean>
```

### 3-4 注入集合属性

- xml配置文件

```java
private String[] stucourses;
private List<String> stulist;
private Map<String, String> stumaps;
private Set<String> stusets;
```

xml配置文件

```xml
<bean id="student" class="com.spring.demo3.Student">
  <!-- 数组 -->
  <property name="stucourses">
    <array>
      <value>语文</value>
      <value>数学</value>
      <value>英语</value>
    </array>
  </property>
  <!-- List类型 -->
  <property name="stulist">
    <list>
      <value>"1"</value>
      <value>"2"</value>
      <value>"3"</value>
    </list>
  </property>
  <!-- Map类型 -->
  <property name="stumaps">
    <map>
      <entry key="java" value="JAVA"></entry>
      <entry key="js" value="JS"></entry>
      <entry key="python" value="python"></entry>
    </map>
  </property>
  <!-- Set类型 -->
  <property name="stusets">
    <set>
      <value>Mysql</value>
      <value>MongoDB</value>
    </set>
  </property>
</bean>
```

集合属性里如果有其他对象的话。

- 新建一个course对象
- student里面写一个List对象 一个set方法
- xml配置文件

```java
/**
 * 课程类 Course
 */
public class Course {
    
    private String cname;

    public String getCname() {
        return this.cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
// student里面写一个List对象 一个set方法
private List<Course> courseList;

public void setCourseList(List<Course> courseList ) {
  this.courseList = courseList;
}
```

xml配置文件 记得新建俩对象

```xml
<!-- 注入List集合 -value是另一个对象 -->
<property name="courseList">
  <list>
    <!-- 创建多个course对象 -->
    <ref bean="courseA"/> 
    <ref bean="courseB"/> 
  </list>
</property>
<!-- 多个course对象 -->
<bean id="courseA" class="com.spring.demo3.Course">
  <property name="cname" value="Spring"></property>
</bean>
<bean id="courseB" class="com.spring.demo3.Course">
  <property name="cname" value="Laravel"></property>
</bean>
```

#### 提取公共集合属性

- 在 spring xml配置文件中引入名称空间 util
- 使用 util 标签完成 list 集合注入提取

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd">
                         
       <bean id="student" class="com.spring.demo3.Student">
           <!-- 注入List集合 -util List -->
           <property name="hobby" ref="hobbyList"></property>
       </bean>

       <util:list id="hobbyList">
            <value>Book</value>
            <value>Music</value>
            <value>Sing</value>
        </util:list>  
</beans>

```

IoC操作Bean（FactoryBean）

在Spring里面有2种Bean

- 自己弄的普通Bean

  - **Bean的class定义什么类型，返回的就是什么类型。**

    `<bean id="userDaoImpl" class="com.spring.demo1.UserDaoImpl">`

- 工厂Bean （FactoryBean）

  - **配置文件中定义的类型可以和返回类型不一样。**

如何写一个工厂Bean

步骤1 普通类实现一个接口

步骤2 重写接口里的方法

步骤3 xml里正常写

步骤4 测试的时候记得要转换成你想要的那个对象类型

```java
// 步骤1 普通类实现一个接口 & 步骤2 重写接口里的方法
public class MyBean implements FactoryBean<Course> {

    // 定义的对象和返回的对象不一致，就是这个方法决定的
    // 定义返回Bean 默认是Object 所以使用泛型返回你想要的Bean
    @Override
    public Course getObject() throws Exception {
        // 这里应该用反射的 为了方便 直接new
        Course course = new Course();
        course.setCname("Japanese");
        return course;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
// 步骤3 xml里正常写
<bean id="myBean" class="com.spring.demo4.MyBean"></bean>
  
// 步骤4 测试的时候记得要转换成你想要的那个对象类型
 public class TestMyBean {

    @Test
    public void testMybean() {
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean4.xml");
        ) {
            // 普通Bean
            // MyBean mb = context.getBean("myBean", MyBean.class);

            // 定义对象和返回对象不一致的Bean 
            // xml定义的是  <bean id="myBean" class="com.spring.demo4.MyBean"></bean>
            // 返回的是 Course
            Course course = context.getBean("myBean", Course.class);
            System.out.println(course); // com.spring.demo3.Course@41fbdac4
        }    
    }
}

```

### 3-5 Bean作用域

- 设置创建 **bean** 实例是单实例还是多实例

- **Spring** 里面，默认情况下，**bean** 是单实例对象

验证代码

说明创建的实例都是一样的 单实例

```java
public class TestStudent {
    
    @Test
    public void testStudent() {
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean3.xml");
        ) {
            Student s1 = context.getBean("student", Student.class);
            Student s2 = context.getBean("student", Student.class);
            System.out.println(s1 == s2); // true
        }
    }
}
```

#### 多实例如何设置

在xml里使用scope属性

- singleton 单实例 默认设置 加载配置文件就会创建
- prototype 多实例 只有在调用`getBean()`才会创建多实例对象

```xml
<bean id="student" class="com.spring.demo3.Student" scope="prototype"></bean>
System.out.println(s1 == s2); // false
```

除了上面的俩，scope还有俩对象 request session 这俩的作用就是创建对象的时候自动加入到requst和session

### 3-6 Bean的生命周期

#### 五步走生命周期

- 通过构造器创建 bean 实例(无参数构造) → 构造器创建
- 为 bean 的属性设置值和对其他 bean 引用(调用 set 方法) → set方法调用
- 调用 bean 的初始化的方法(需要进行配置初始化的方法) → 初始化方法xml要注意
- bean 可以使用了(对象获取到了) →  正常使用
-  当容器关闭时候，调用 bean 的销毁的方法(需要进行配置销毁的方法) → 销毁xml要注意

```java
// BeanLife类
public class BeanLife {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("第二步 调用 set 方法设置属性值 setName(String name)");
    }

    public BeanLife() {
        System.out.println("第一步 执行无参数构造创建 bean 实例 BeanLife()");
    }
    
    public void initMethod() {
        System.out.println("第三步 执行初始化的方法 initMethod()");
    }
    
    public void destoryMethod(){
        System.out.println("第五步 执行销毁的方法destoryMethod()");
    }
}
// xml配置文件
<bean id="beanLife" class="com.spring.demo4.BeanLife" init-method="initMethod" destroy-method="destoryMethod">
  <property name="name" value="BeanLife Yeah~~"></property>
</bean>
// 测试文件
public void testBeanLife() {
       try (
        ClassPathXmlApplicationContext context = 
            new ClassPathXmlApplicationContext("bean4.xml");
       ) {
           BeanLife bl = context.getBean("beanLife", BeanLife.class);
           System.out.println("第四步 获取创建 bean 实例对象");
           System.out.println(bl);
       }
}

    // 第二步 调用 set 方法设置属性值 setName(String name)
    // 第三步 执行初始化的方法 initMethod()
    // 第四步 获取创建 bean 实例对象
    // com.spring.demo4.BeanLife@131774fe
    // 第五步 执行销毁的方法destoryMethod()
```

#### 七步走生命周期（5+2个后置处理器）

其实就是比上面多了2个后置处理器，这个后置处理器需要在xml里面配置，并且需要实现一个接口*BeanPostProcessor*

这个接口有俩方法。一个在初始化之前执行，一个在初始化之后执行。

- 通过构造器创建 bean 实例(无参数构造)
- 为 bean 的属性设置值和对其他 bean 引用(调用 set 方法)
- **<u>把 bean 实例传递 bean 后置处理器的方法 postProcessBeforeInitialization</u>** → 新增 初始化之前
- 调用 bean 的初始化的方法(需要进行配置初始化的方法)
- **<u>把 bean 实例传递 bean 后置处理器的方法 postProcessAfterInitialization</u>** → 新增 初始化之后
- bean 可以使用了(对象获取到了)
- 当容器关闭时候，调用 bean 的销毁的方法(需要进行配置销毁的方法)

```java
public class BeanLife  implements BeanPostProcessor {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("第二步 调用 set 方法设置属性值 setName(String name)");
    }

    public BeanLife() {
        System.out.println("第一步 执行无参数构造创建 bean 实例 BeanLife()");
    }
    
    public void initMethod() {
        System.out.println("第三步 执行初始化的方法 initMethod()");
    }
    
    public void destoryMethod(){
        System.out.println("第五步 执行销毁的方法destoryMethod()");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("在初始化之前执行的方法 Before...");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("在初始化之后执行的方法 After...");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}

// xml配置
<!-- 配置后置处理器 效果是全局的这里的xml所有的对象都适用-->
<bean id="beanLife" class="com.spring.demo4.BeanLife" init-method="initMethod" destroy-method="destoryMethod">
  <property name="name" value="BeanLife Yeah~~"></property>
</bean>
```

### 3-7 Bean属性自动装配

实际中很少适用，一般都用注解。

什么是自动装配？根据制定规则装备规则（名称or类型），Spring将自动匹配的属性值注入进去。

```xml
<!-- 手动装配 指定 dept注入值 -->
<bean id="employee" class="com.spring.demo2.Employee">
   <property name="dept" ref="dept"></property>
</bean>
<bean id="dept" class="com.spring.demo2.Depart">
  <property name="dname" value="Manager"></property>
</bean>
<!-- 自动装配 指定 dept注入值 -->
<!--  autowire="byName" 名字 这个名称要和对象id一致。配置自动装配-->
<!--  autowire="byType" 类型 配置自动装配 -->
<bean id="employee" class="com.spring.demo2.Employee" autowire="byName">
  <property name="ename" value="Tom"></property>
  <property name="gender" value="女"></property>
</bean>
<bean id="dept" class="com.spring.demo2.Depart">
  <property name="dname" value="Manager"></property>
</bean>
```

> 引入外部属性文件
>
> 步骤1 引入包
>
> 步骤2 写xml文件 ↓有
>
> 步骤3 写jdbc.properties文件

```xml
<!-- 直接配置连接池  -->
<!-- <bean id="dataSource" class="com.druid.pool.DruidDataSource">
            <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
            <property name="url" value="jdbc:mysql://localhost:3306/test"></property>
            <property name="username" value="root"></property>
            <property name="password" value="root"></property>
       </bean> -->

<!-- 引入外部属性文件 -->
<context:property-placeholder location="classpath:jdbc./jdbc.properties"/>
<bean id="dataSource" class="com.druid.pool.DruidDataSource">
  <property name="driverClassName" value="${prop.driverClass}"></property>
  <property name="url" value="${prop.url}"></property>
  <property name="username" value="${prop.user}"></property>
  <property name="password" value="${prop.password}"></property>
</bean>
```

## **3. IoC操作Bean之「注解」**

> 1. 什么是注解？**就是一些有含义的特殊标记。**
>
> 形式 →`@注解（属性name=属性value）` 
>
> 2. 注解可以在哪里？**类，属性，方法。**
>
> 注解的目的是什么？**为了让配置更加简洁，简化xml配置。**
>
> 3. Bean管理有哪些注解？以下4个都可以创建实例。
>
> - @Component → 普通注解
> - @Service→ 业务逻辑层
> - @Controller→ web层
> - @Repository → 持久层上面
>
> **虽然主要用在不同的层，但其实互相都可以用。上面4个注解功能都是一样。都可以用来创建Bean对象。**

### 基于注解方式实现Bean对象创建步骤

> **步骤1** 引入AOP依赖 `spring-aop-5.2.6.RELEASE.jar`
>
> **步骤2** xml配置文件开启组件扫描（什么是组件扫描，告诉容器在哪里类加了注解，指定了扫描位置。）
>
>  - 引入context名称空间
>  - 开启组件扫描
>
> `<context:component-scan base-package="扫描的包"></context:component-scan>`
>
> 1 多个扫描的包可以用逗号隔开。 `com.a.b,com.a.c` 
>
> 2 扫描包的上层目录`com.a`
>
> **步骤3** 创建类，添加注解注释。
>
> 	- 上面4个都可以。*@Component(value="userService")* 类似于。`<Bean id="userService", class="..."/>`
> 	- 注解里面 value 属性值可以省略不写。不写就是默认是类名小写。
> 	- 默认值是类名称，首字母小写 UserService → userService
>
> **步骤4** 测试类。
>
> 现在开始代码演示一下了。

```xml
<!-- 开启组件扫描 也可以使用具体包逗号分隔开，com.spring.demo1,com.spring.demo2 -->
<context:component-scan base-package="com.spring"></context:component-scan>
```

java如下正常些。除了加了注解。

```java
// 其他三个也可以写Component(value="anoStudent") 默认是类名的首字母小写
@Component
public class AnoStudent {

    public void show() {
        System.out.println("AnoStudent show()...");
    }
}
// 测试类正常写
public class TestAnoStudent {

    @Test
    public void testAnoStudent() {
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean6.xml");
        ) {
            AnoStudent as = context.getBean("anoStudent", AnoStudent.class);
            System.out.println(as);
            as.show();
        }
    }
}
```

### 组件扫描filter

其实这个就相当于是白名单黑名单的感觉，**1 只扫描XX 2全部扫描除了XX。**这样。

白名单（自己取名的）

```xml
<!-- 开启组件扫描 白名单（自己取名 官方不用的）-->
<!-- use-default-filters="false" 表示现在不使用默认 filter，自己配置 filter -->
<!-- context:include-filter ，设置扫描哪些内容 -->
<context:component-scan base-package="com.spring" use-default-filters="false">
  <!-- 比如这里就是只是扫描四大金刚里面的 Controller -->
  <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller" />
</context:component-scan>
```

黑名单

```xml
<!-- 开启组件扫描 黑名单（自己取名 官方不用的）-->
<!-- context:exclude-filter: 设置哪些内容不进行扫描 -->
<context:component-scan base-package="com.spring">
  <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller" />
</context:component-scan>
```

### 注解方式实现属性注入

- @AutoWired 根据属性类型
- @Qualifier 根据属性名称 【这个要和AutoWired 一起使用，用来特定对象是什么】
- @Resource 类型or名称都可以 是javax并不是Spring包里面的。Spring官方不建议使用。

以上三个都是对象

- @Value → 针对普通类型 

具体代码实现。

步骤1  写一个dao类接口 + 实现类（要有注解）

步骤2 写一个service类 （要有注解）

步骤3 测试写法

```java
// 接口
public interface AniDao {
    public void show();
}

// 实现类
// 不写这个value 就是默认首字母小写那种
@Repository(value = "aaaaa")
public class AniDaoImpl implements AniDao  {
    @Override
    public void show() {
        System.out.println("AniDaoImpl show...");
    }
}
// service类
// @Component(value="aniService")
@Service
public class AniService {
    // 不需要set方法
    // Autowired 属性类型
    @Autowired
    // @Qualifier(value = "aaaa") 不写这个value 就是默认首字母小写那种
    @Qualifier(value = "aaaaa")
    private AniDao aniDao;

    public void add() {
        System.out.println("AniService add");
        aniDao.show();
    }
}
xml就只是开启个扫描而已
<!-- 开启组件扫描 -->
<context:component-scan base-package="com.spring"></context:component-scan>

```

**@Resource** 有一个问题就是不属于Spring标准库里面的

```java
@Resource 默认是属性
@Resource(name="") 也可以根据名字 
```

**@Value** 可以注入普通数据类型

```java
@Value(value="")
@Service
public class AniService {
    
    @Value(value="1234ab")
    private String name;

}
```

#### 完全注解开发

上面的xml 只用了那一行 实在太鸡肋了。

`<context:component-scan base-package="com.spring"></context:component-scan>`

步骤1 创建配置类 替代xml 在任一地方写

```java
/**
 * 配置类
 */
@Configuration
@ComponentScan(basePackages = {"com.spring"})
public class SpringConfig {
    
}
```

步骤2 测试类

```java
try (
  // ClassPathXmlApplicationContext context = 
  //     new ClassPathXmlApplicationContext("bean7.xml");

  // 完全使用注解开发
  AnnotationConfigApplicationContext context = 
  new AnnotationConfigApplicationContext(SpringConfig.class);

) {
  AniService as = context.getBean("aniService", AniService.class);
  as.add();
}
```

## 4. AOP (Aspect Oriented Programming)

### 什么是AOP？

面向切面编程。软件开发的一个方法。

我理解的就是在不修改原来代码的情况下，增加新的功能。降低耦合度，便于开发。

看了一下日网，**Aspect**翻译成了**横断的**，感觉表达的感觉就是给现有的程序从中间插入进去，横断，的插入了一个新功能。

![SpringFrameworkでAOP(アスペクト指向プログラミング)(Javaアプリケーション)](https://tokkan.net/spring/sp016.png)

### AOP底层原理是？

底层原理就是**动态代理**。

- 有接口的情况下。JDK动态代理。 **创建<u>接口实现类</u>代理对象进行代理。**
- 没借口的情况下。CGLIB动态代理。**创建<u>当前子类</u>代理对象进行代理。**

##### 先写一个静态代理的例子。

**特点 代理类和被代理类在编译期间已经被确定**

就写一个机器人代替人来工作的例子吧。

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Human h = new Human();
        Robot r = new Robot(h);
       // 调用的是代理类的执行方法，实际执行的是被代理类
        r.work();
    }
}
// 接口定义规范
interface Earth {
    void work();
}
// 被代理类--人类
class Human implements Earth {
    
    @Override
    public void work(){
        System.out.println("Human work...");
    }
}
// 代理人--机器人
class Robot implements Earth {
    
    private Earth earth;
    public Robot(Earth earth){
        this.earth = earth;
    }
    public void robotCheck(){
        System.out.println("Robot robotCheck...");
    }
    
    @Override
    public void work(){
        robotCheck();
        earth.work();
    }
}
```

##### 动态代理的例子。

看了这么多动态代理，总结起来就是静态代理的时候不是弄了一个被代理类和代理类吗。

动态代理的话，Java里面有一个类叫*Proxy* 然后这个接口有一个方法可以生成动态代理类的实例，叫*newProxyInstance*

```java
// 参数1 加载器 参数2 接口 参数3 要实现InvocationHandler 这个接口
public static Object newProxyInstance(ClassLoader loader,
                                      Class<?>[] interfaces,
                                      InvocationHandler h)
```

现在演示一个通过**动态代理**增加功能的例子。

- 一个接口
- 一个接口实现类
- 一个实现动态代理的类
- 一个测试类

```java
/**
 * 接口 Robot
 */
public interface DynamicRobot {
    String getEnergy();
    void work(String time);
}

/**
 * 接口实现类
 * DynamicRobotImpl
 */
public class DynamicRobotImpl implements DynamicRobot {

    @Override
    public String getEnergy() {
        return "DynamicRobotImpl getEnergy()...";
    }

    @Override
    public void work(String time) {
        System.out.println("DynamicRobotImpl work" + time);
    }
}
```

这个动态代理实现类是重点，于是我单独写一下

```java
/**
 * 实现动态代理需要解决的问题
 * 动态代理就是根据被代理类而创建代理类和实现方法，那么问题来了。
 * 问题1 如何根据加载到内存的被代理类，动态的创建一个代理类和对象
 * 问题2 当通过代理类的对象调用方法a是，如何动态的去调用被代理类的同名方法a
 */
public class DynamicProxy {
	
  	// 这里的 Object 就是被代理类的对象，通过此方法，就要返回一个代理类的对象 解决问题1
    // 也就是进入被代理类，出来代理类
    public static Object getProxyInstance(Object obj) {
				// getClass() → 返回运行时类
        // getClassLoader() → 返回类的加载器
        // 为什么getInterfaces() 要接口，因为被代理类和代理类都要实现同一个接口
        // 参数1 获取被代理类的类
        // 参数2 获取被代理的接口
        // 参数3 解决问题2
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

        // 这个方法的主要作用就是执行代理类的a方法，就会自动执行被代理类的同名a方法 返回值是一个对象
        Object returnVal = method.invoke(obj, args);

        System.out.println("增强之后的我...");

        return returnVal;
    }
}

```

### 4-3 术语解释

- 连接点 → joinpoint 类里面那些方法可以被增强 可以被增加的方法
- 切入点 → pointcut 真正增强的方法 实际上被增强的方法
- 通知 → advice 实现增强的逻辑部分
  - 前置
  - 后置
  - 异常
  - 最终
  - 环绕
- 切面 aspect → 把通知运用到切入点的过程

**AOP准备工作**

1. AspectJ不是Spring组成部分，是独立的AOP框架。一般 **AspectJ** + **Spring**就可以实现操作

基于 AspectJ 有2种实现方式

- xml
- 注解 **<u>一般使用注解</u>**

2. 引入相关依赖 *spring-aspects-5.2.6.RELEASE.jar*

事实证明 仅有上面的jar包是不行的。还需要添加以下

```
com.springsource.net.sf.cglib-2.2.0.jar
com.springsource.org.aopalliance-1.0.0.jar
com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar
```

3. **切入点表达式【重点】**

```
作用 知道对哪个类里面的哪个方法进行增强
语法结构 execution([权限修饰符] [返回类型：可省略] [类全路径] [方法名称]([参数列表]) )

例子1 对com.spring.demo包里面Student类的show（）方法进行增强
execution(* com.spring.demo.Student.show(..)) 参数..可以省略 返回类型可以省略

例子2 对com.spring.demo包里面Student类的所有方法进行增强
execution(* com.spring.demo.Student.*(..))

例子3 对com.spring.demo包里面所有类的所有方法进行增强
execution(* com.spring.demo.*.*(..))
```

### AOP具体操作

#### 基于注解

> 步骤1 创建被增强类，在类面定义方法
>
> 步骤2 创建增强类，书写增强逻辑
>
> 步骤3 xml配置一下

以上就是准备工作，写完之后应该是这样的。

```java
/**
 * 被增强类 User 用来验证AOP Aspect注解
 */
public class AopAnoUser {
    
    public void show() {
        System.out.println("AopAnoUser show()...");
    }
}
/**
 * 增强类
 */
public class EnhanceAopAnoUser {
    // 前置通知
    public void beforeAno() {
        System.out.println("beforeAno()");
    }
    // 后置通知
    public void afterAno() {
        System.out.println("afterAno()");
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
     <!-- 开启组件扫描 -->
     <context:component-scan base-package="com.spring.aopannotion"></context:component-scan>
     <!-- 开启Aspect生成代理对象 其实本质就是寻找是否有 Aspect注解 找到之后生成代理对象 -->
     <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
</beans>

```

准备工作完成之后开始增强了！！

```java

```

通知的顺序问题

| 通知           | 执行时机                                                     | 备注 |
| -------------- | ------------------------------------------------------------ | ---- |
| Before         | **前置通知**:在原来方法之前执行                              |      |
| AfterReturning | **后置通知**:在原来的方法之后执行。特点:可以得到被增强方法的返回值。 |      |
| After          | **最终通知**:指的是无论是否有异常，总是被执行。              |      |
| AfterThrowing  | **异常通知**:目标方法出现异常执行，如果方法没有异常，不会执行。特点:可以获得异常的信息。 |      |
| Around         | **环绕通知**:在方法之前和方法之后执行。                      |      |

```
环绕之前....
前置通知@Before beforeAno()...
AopAnoUser show()...
环绕之后....
最终通知@After afterAno()...
后置通知@AfterReturning afterReturningAno()...

如果在被增强的方法内部发生了异常
环绕之前....
前置通知@Before beforeAno()...
最终通知@After afterAno()...
异常通知@AfterThrowing afterThrowingAno()...

结论也就是 
环绕之后....
AfterReturning没执行
也就是说After 类似于 finally
```

#### 抽取公共的切入点

为什么要抽取呢。因为你看上面的代码，每一次切入点的时候都写一堆的那玩意儿多烦躁。

所以这样也可以设置

```java
// 抽取相同的切入点设置 
// 不想写这一坨
// value = "execution(* com.spring.aopannotion.AopAnoUser.show(..))"
@Pointcut(value = "execution(* com.spring.aopannotion.AopAnoUser.show(..))")
public void pointCut() {

}
    
// 前置通知
// Before 注解表示前置通知 这个() 不能忘记啊 毕竟是个方法
@Before(value = "pointCut()")
public void beforeAno() {
  System.out.println("前置通知@Before beforeAno()...");
}
```

#### 多个增强类，设置个优先级。

比如又来了一个增强类 那么谁优先呢。？

可以通过在类上指定order，order越小越是最先执行。

```java
// 为了验证 新建一个增强类
@Component
@Aspect
@Order(1)
public class EnhanceAopAnoUserMore {
    
    @Before(value = "execution(* com.spring.aopannotion.AopAnoUser.show(..))")
    public void beforeAnoMore() {
        System.out.println("EnhanceAopAnoUserMore @Before beforeAnoMore()");
    }
}
```

#### 完全注解开发

意思就是说，不需要xml了。全部注解。

大概是这样子 创建配置类

```java
@Configuration
@ComponentScan(basePackages = {"com.spring"})
// 替代品 默认是true <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ConfigAop {
    
}
// 测试类
public void testEnhanceAopAnoUser() {
        try (
            // ClassPathXmlApplicationContext context = 
            //     new ClassPathXmlApplicationContext("bean8.xml");

            // 完全使用注解开发
            AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(ConfigAop.class);
        ) {
            AopAnoUser aau = context.getBean("aopAnoUser", AopAnoUser.class);
            aau.show();
        }
    }
```

#### 基于XML配置文件

这个只是了解，很少使用。

了解一下步骤就可以

> - 创建增强类和被增强类，创建方法
> - **Spring**配置文件中创建两个类对象
> - 在 **Spring** xml配置文件中配置切入点

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
     
     <!-- 创建对象 -->
     <bean id="book" class="com.spring.aopxml.BookXml"></bean>
     <bean id="enhancebook" class="com.spring.aopxml.EnhanceBookXml"></bean>

     <!-- 配置aop增强 -->
     <aop:config>
          <!-- 配置切入点 -->
          <!-- 1切入点表达式 -->
          <aop:pointcut id="p" expression="execution(* com.spring.aopxml.BookXml.buy(..))" />
          <!-- 2切面 -->
          <aop:aspect ref="enhancebook">
               <!-- 增强作用在哪里 -->
               <aop:before method="beforeBuyBook" pointcut-ref="p"/>
          </aop:aspect>
     </aop:config>

</beans>
```

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20210209232034.png)

### 5. JdbcTemplate

其实就是对JDBC的进一步封装。

`JDBC + Spring（进一步封装）= JdbcTemplate`

#### 相关步骤

##### 1 配置连接池

```
prop.username=root
prop.password=root
prop.url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true
prop.driverClass=com.mysql.cj.jdbc.Driver
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context 
                           http://www.springframework.org/schema/context/spring-context.xsd
                           ">
    
    
    <!-- 开启组件扫描 -->
    <context:component-scan base-package="com.jdbctemp"></context:component-scan>

    <!-- 加载配置文件 -->
    <context:property-placeholder location="classpath:jdbc.properties" />
    <!-- 数据库连接池 -->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" destroy-method="close">
        <property name="driverClassName" value="${prop.driverClass}"></property>
        <property name="username" value="${prop.username}"></property>
        <property name="password" value="${prop.password}"></property>
        <property name="url" value="${prop.url}"></property>
    </bean>
    <!-- <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" destroy-method="close">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"></property>
        <property name="username" value="root"></property>
        <property name="password" value="root"></property>
        <property name="url" value="jdbc:mysql:///test"></property>
    </bean> -->

    <!-- jdbTemplate对象 -->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <!-- 注入 dataSource -->
        <property name="dataSource" ref="dataSource"></property>
    </bean>

</beans>
```

##### 2 引入相关jar包

##### 3.写java文件

- UserDao接口 → 象征性写方法
- UserDaoImpl实现类 → 实现方法&注入*JdbcTemplate*&写逻辑
- UserService → 注入UserDao 相当于连接dao和实体类 **最后测试的时候使用的还是这个 把userDao导入** 
- User → 实体类
- 测试类UserTest

*UserDao.java*

```java
package com.jdbctemp.dao;

import com.jdbctemp.entity.User;

public interface UserDao {
    void add(User user);
}
```

*UserDaoImpl*

```java
package com.jdbctemp.dao;

import com.jdbctemp.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    // JdbcTemplate 因为要在这里实现数据库操作
    @Autowired
    private JdbcTemplate jdbcTemplate; 

    // 实现添加操作
    @Override
    public void add(User user) {
        String sql = "insert into user values(?,?,?,?,?)";
        Object[] args = {user.getId(), user.getName(), user.getPassword(), 
                        user.getAddress(), user.getPhone()};
        int update = jdbcTemplate.update(sql, args);
        if (update != 0) {
            System.out.println("success");
        } else {
            System.out.println("error");
        }
    }
}
```

*UserService*

```java
package com.jdbctemp.service;

import com.jdbctemp.dao.UserDao;
import com.jdbctemp.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // 注入dao
    @Autowired
    private UserDao userDao; 

    public void addUser(User user) {
        userDao.add(user);
    }
}
```

*User*

```java
package com.jdbctemp.entity;

public class User {
    
    private int id;
    private String name;
    private String password;
    private String address;
    private String phone;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
```

测试类

```java
package com.jdbctemp.test;

import com.jdbctemp.entity.User;
import com.jdbctemp.service.UserService;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {
    
    @Test
    public void testAddUser(){
        try (
            ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("bean.xml");
        ) {
            UserService userService = context.getBean("userService",UserService.class);
            User user = new User();
            user.setId(9);
            user.setName("Amy2");
            user.setPassword("password");
            user.setAddress("151-0053 ");
            user.setPhone("111111");
            userService.addUser(user);
        }
    }
}
```

#### 其他操作（更新&删除&查询操作）

- 更新删除 → *update*
- 查询操作 → *Integer count = jdbcTemplate.queryForObject(sql, Integer.class);* 注意这里的参数是一个需要返回什么类型，参数就要用那个类型的对象
- 查询操作（返回对象）→ *jdbcTemplate.**queryForObject**(sql, new **BeanPropertyRowMapper<User>(User.class), id);*** 注意接口
- 查询操作（返回列表）→ *jdbcTemplate.**query**(sql, new **BeanPropertyRowMapper**<User>(User.class));* 注意接口。

```java
// 实现更新操作
    @Override
    public void update(User user) {
        String sql = "update user set id=?,name=?,password=?,address=?,phone=? where id=?";
        Object[] args = { user.getId(), user.getName(), user.getPassword(), user.getAddress(), user.getPhone(),
                user.getId() };
        int result = jdbcTemplate.update(sql, args);
        if (result != 0) {
            System.out.println("success");
        } else {
            System.out.println("error");
        }
    }

    // 实现删除操作
    @Override
    public void delete(int id) {
        String sql = "delete from user where id=?";
        int result = jdbcTemplate.update(sql, id);
        if (result != 0) {
            System.out.println("success");
        } else {
            System.out.println("error");
        }
    }

    // 实现查询数据操作
    @Override
    public int selectCount(User user) {
        String sql = "select count(*) from user";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    // 查询单个数据-返回对象
    @Override
    public User findUser(int id) {
        String sql = "select * from user where id=?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        return user;
    }

    // 查询单个数据-返回集合
    @Override
    public List<User> findAllUsers() {
        String sql = "select * from user";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

```

感觉总结一下。其实就是什么吧。

**学会框架 + 调用API**

