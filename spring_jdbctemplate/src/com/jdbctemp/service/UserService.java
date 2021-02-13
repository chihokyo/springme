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

    // 添加User
    public void addUser(User user) {
        userDao.add(user);
    }

    // 更新User
    public void updateUser(User user) {
        userDao.update(user);
    }

    // 删除User
    public void deleteUser(int id) {
        userDao.delete(id);
    }

    // 查询 User 有多少条数据
    public int findCount(User user) {
        return userDao.selectCount(user);
    }

    // 查询 User 返回Object
    public User findUserObject(int id) {
        return userDao.findUser(id);
    }

    // 查询 User 返回List
    public List<User> findUserList() {
        return userDao.findAllUsers();
    }

    // 批量添加User
    public void batchAdd(List<Object[]> batchArgs) {
        userDao.batchAddUsers(batchArgs);
    }

    // 批量更新User
    public void batchUpdate(List<Object[]> batchArgs) {
        userDao.batchUpdateUser(batchArgs);
    }

    // 批量删除User
    public void batchDelete(List<Object[]> batchArgs) {
        userDao.batchDeleteUser(batchArgs);
    }
}
