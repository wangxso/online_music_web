package com.demo.service.impl;

import com.demo.common.Result;
import com.demo.mapper.UserMapper;
import com.demo.pojo.User;
import com.demo.pojo.UserExample;
import com.demo.service.UserService;
import com.demo.utils.UUIDGenrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
/**
 * @author wangx
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findAllUser() {
        List<User> userList = userMapper.selectByExample(null);
        return userList;
    }

    @Override
    public Result addUser(User user) {
        System.out.println(user.getPassword());
        user.setUserId(UUIDGenrator.getUUID());
        user.setCreateTime(new Date());
        //初始为0级
        user.setLevel(0);
        userMapper.insert(user);
        return new Result("成功",200);
    }

    @Override
    public User findOneUser(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Result login(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNickNameEqualTo(user.getNickName());
        List<User> userNow = userMapper.selectByExample(example);
        if(userNow.size() > 0){
            if(userNow.get(0).getPassword().equals(user.getPassword())){
                return new Result("成功",200);
            }else{
                return new Result("失败",500);
            }
        }
        return new Result("失败",500);
    }

    @Override
    public User findByUserName(String userName) {
        com.demo.pojo.UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNickNameEqualTo(userName);
        List<User> users = userMapper.selectByExample(example);
        if(users.size()>0){
            return users.get(0);
        }else{
            return null;
        }

    }

    @Override
    public User updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
        return null;
    }
}
