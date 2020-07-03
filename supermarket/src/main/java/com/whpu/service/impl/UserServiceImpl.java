package com.whpu.service.impl;

import com.whpu.mapper.UserMapper;
import com.whpu.pojo.User;
import com.whpu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findByMobileAndPwd(String userName, String userPassword) {
        return userMapper.findByMobileAndPwd(userName,userPassword);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public List<User> findLike(String condition) {
        return userMapper.findLike(condition);
    }

    @Override
    public void updatePwd(String userPassword, Integer id) {
        userMapper.updatePwd(userPassword,id);
    }
}
