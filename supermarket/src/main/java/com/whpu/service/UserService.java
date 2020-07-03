package com.whpu.service;

import com.whpu.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserService
 * @Description: 用户服务层接口
 * @author: 10542
 * @date: 2020/6/28 22:37
 */

public interface UserService {

    public List<User> findAll();

    public User findByMobileAndPwd(String userName, String userPassword);

    public User findById(Integer id);

    public User findByUserName(String userName);

    void save(User user);

    void delete(Integer id);

    void update(User user);

    List<User> findLike(String condition);

    void updatePwd(String userPassword,Integer id);
}
