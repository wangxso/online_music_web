package com.demo.service;

import com.demo.common.Result;
import com.demo.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 获取用户列表
     * @return
     */
    public List<User> findAllUser();

    /**
     * 添加用户
     * @return
     */
    public Result addUser(User user);

    /**
     * 查找单个用户
     * @param id
     * @return
     */
    public User findOneUser(String id);

    /**
     * 用户登录
     * @param user
     * @return
     */
    public Result login(User user);

    /**
     * 根据用户名查找
     * @param userName
     * @return
     */
    public User findByUserName(String userName);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public User updateUser(User user);

}
