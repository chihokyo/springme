package com.jdbctemp.dao;

import com.jdbctemp.entity.User;

public interface UserDao {

    void add(User user);

    void update(User user);

    void delete(int id);
}
