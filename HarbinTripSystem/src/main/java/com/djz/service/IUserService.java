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
     *
     * @param user
     * @return
     */
    public boolean login(User user);

    /**
     * 获取用户信息判断是否为合法用户
     *
     * @param username
     * @return
     */
    public User getUser(String username);

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 根据用户名修改密码
     *
     * @param name
     * @param pass
     * @return
     */
    int updateUserByName(String name, String pass);
}
