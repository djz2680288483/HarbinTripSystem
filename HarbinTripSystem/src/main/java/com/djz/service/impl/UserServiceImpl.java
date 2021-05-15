package com.djz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.djz.entity.User;
import com.djz.mapper.UserMapper;
import com.djz.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sr
 * @since 2019-01-18
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(User user) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.lambda().eq(User::getPassword, user.getPassword());
        query.lambda().eq(User::getName, user.getName());
        return userMapper.selectObjs(query) != null;
    }

    @Override
    public User getUser(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getName, username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateUserByName(String name, String pass) {
        UpdateWrapper<User> query = new UpdateWrapper<>();
        query.lambda().eq(User::getName, name);
        User user = new User();
        user.setUpdateTime(new Date());
        user.setPassword(pass);
        return userMapper.update(user, query);
    }
}
