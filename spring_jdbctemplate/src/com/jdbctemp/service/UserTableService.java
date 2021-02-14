package com.jdbctemp.service;

import com.jdbctemp.dao.UserTableDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// (1)@Transactional，这个注解添加到类上面，也可以添加方法上面 
// (2)如果把这个注解添加类上面，这个类里面所有的方法都添加事务 
// (3)如果把这个注解添加方法上面，为这个方法添加事务

@Service
@Transactional
public class UserTableService {
    
    @Autowired
    private UserTableDao userTableDao;

    public void accuntMoney() {
        // 少钱操作
        userTableDao.reduceMoney();
        // 多钱操作
        userTableDao.addMoney();
    }
}
