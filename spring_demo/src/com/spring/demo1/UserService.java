package com.spring.demo1;

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
