package com.tianshi.songzeyang.service.impl;

import com.tianshi.songzeyang.bean.User;
import com.tianshi.songzeyang.mapper.UserMapper;
import com.tianshi.songzeyang.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 用户注册
     */
    public boolean register(User user) {
        // 业务校验：用户名已存在，抛出异常
        return userMapper.insert(user) > 0;
    }

    /**
     * 用户登录
     */
    public User login(String username, String password) {
        User condition = new User();
        condition.setUsername(username);
        condition.setPassword(password);
        // 多条件精确查询，返回用户对象
        return userMapper.selectMultiCondition(condition);
    }

    /**
     * 根据ID删除用户
     */
    public boolean removeUserById(Integer id) {
        return userMapper.deleteById(id) > 0;
    }

    /**
     * 批量删除用户
     */
    public boolean removeUsersByIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        return userMapper.deleteByIds(ids) > 0;
    }

    /**
     * 多条件更新用户
     */
    public boolean modifyUser(User user) {
        if (user.getId() == null) {
            return false;
        }
        return userMapper.update(user) > 0;
    }

    /**
     * 查询所有用户
     */
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    /**
     * 用户名模糊查询
     */
    public List<User> queryUserByUsernameLike(String username) {
        return userMapper.selectByUsernameLike(username);
    }

    /**
     * 多条件精确查询（通用）
     */
    public User queryUserByCondition(User user) {
        return userMapper.selectMultiCondition(user);
    }

    /**
     * 检查用户名是否存在
     */
    public boolean checkUsernameExist(String username) {
        User condition = new User();
        condition.setUsername(username);
        return userMapper.selectMultiCondition(condition) != null;
    }

    /**
     * 验证用户名和密码是否匹配
     */
    public boolean verifyUsernameAndPassword(String username, String password) {
        User condition = new User();
        condition.setUsername(username);
        condition.setPassword(password);
        return userMapper.selectMultiCondition(condition) != null;
    }

}