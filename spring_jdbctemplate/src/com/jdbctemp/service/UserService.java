package com.jdbctemp.service;

import java.util.List;

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

    public void updateUser(User user) {
        userDao.update(user);
    }

    public void deleteUser(int id) {
        userDao.delete(id);
    }

    public int findCount(User user) {
        return userDao.selectCount(user);
    }

    public User findUserObject(int id) {
        return userDao.findUser(id);
    }

    public List<User> findUserList() {
        return userDao.findAllUsers();
    }
}
