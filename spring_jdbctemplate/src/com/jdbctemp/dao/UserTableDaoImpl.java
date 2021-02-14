package com.jdbctemp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserTableDaoImpl implements UserTableDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void addMoney() {
        String sql = "update user_table set balance=balance+? where user=?";
        Object[] args = {100, "AA"};
        jdbcTemplate.update(sql, args);

    }

    @Override
    public void reduceMoney() {
        String sql = "update user_table set balance=balance-? where user=?";
        Object[] args = {100, "BB"};
        jdbcTemplate.update(sql, args);
    }
    
}
