package com.jdbctemp.dao;

import java.util.List;

import com.jdbctemp.entity.User;

public interface UserDao {

    void add(User user);

    void update(User user);

    void delete(int id);

    int selectCount(User user);

    User findUser(int id);

    List<User> findAllUsers();

    void batchAddUsers(List<Object[]> batchArgs);

    void batchUpdateUser(List<Object[]> batchArgs);

    void batchDeleteUser(List<Object[]> batchArgs);

}
