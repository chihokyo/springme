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
