package com.djz.service;


import com.djz.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author sr
 * @since 2019-01-18
 */
public interface IUserService {
    /**
     * 用户登录
     * @param user
     * @return
     */
    public boolean login(User user);

    /**
     * 获取用户信息判断是否为合法用户
     * @param username
     * @return
     */
    public User getUser(String username);
}
