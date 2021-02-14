package com.jdbctemp.service;

import com.jdbctemp.dao.UserTableDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTableService {
    
    @Autowired
    private UserTableDao userTableDao;

    public void accuntMoney() {
        // 少钱操作
        userTableDao.reduceMoney();
        int i = 10/0;
        // 多钱操作
        userTableDao.addMoney();
    }
}
