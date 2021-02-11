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
