package com.jdbctemp.dao;

import java.util.Arrays;
import java.util.List;

import com.jdbctemp.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        Object[] args = { user.getId(), user.getName(), user.getPassword(), user.getAddress(), user.getPhone() };
        int update = jdbcTemplate.update(sql, args);
        if (update != 0) {
            System.out.println("success");
        } else {
            System.out.println("error");
        }
    }

    // 实现更新操作
    @Override
    public void update(User user) {
        String sql = "update user set id=?,name=?,password=?,address=?,phone=? where id=?";
        Object[] args = { user.getId(), user.getName(), user.getPassword(), user.getAddress(), user.getPhone(),
                user.getId() };
        int result = jdbcTemplate.update(sql, args);
        if (result != 0) {
            System.out.println("success");
        } else {
            System.out.println("error");
        }
    }

    // 实现删除操作
    @Override
    public void delete(int id) {
        String sql = "delete from user where id=?";
        int result = jdbcTemplate.update(sql, id);
        if (result != 0) {
            System.out.println("success");
        } else {
            System.out.println("error");
        }
    }

    // 实现查询数据操作
    @Override
    public int selectCount(User user) {
        String sql = "select count(*) from user";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    // 查询单个数据-返回对象
    @Override
    public User findUser(int id) {
        String sql = "select * from user where id=?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        return user;
    }

    // 查询单个数据-返回集合
    @Override
    public List<User> findAllUsers() {
        String sql = "select * from user";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }
    
    // 批量添加数据User
    @Override
    public void batchAddUsers(List<Object[]> batchArgs) {
        String sql = "insert into user values(?,?,?,?,?)";
        // 底层的话会给你遍历Object
        int[] result = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(result));
    }

    // 批量更新数据User
    @Override
    public void batchUpdateUser(List<Object[]> batchArgs) {
        String sql = "update user set name=?,password=?,address=?,phone=? where id=?";
        // 底层的话会给你遍历Object
        int[] result = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(result));
    }

    // 批量删除数据User
    @Override
    public void batchDeleteUser(List<Object[]> batchArgs) {
        String sql = "delete from user where id=? ";
        // 底层的话会给你遍历Object
        int[] result = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(result));
    }
}
