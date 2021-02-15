package com.djz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.djz.entity.User;
import com.djz.mapper.UserMapper;
import com.djz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sr
 * @since 2019-01-18
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(User user) {
        QueryWrapper<User> ew = new QueryWrapper<>();
        ew.eq("id", user.getId());
        ew.eq("password", user.getPassword());
        return userMapper.selectObjs(ew) != null;
    }

    @Override
    public User getUser(String id) {
        return userMapper.selectById(id);
    }
}
