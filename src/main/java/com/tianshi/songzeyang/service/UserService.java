package com.tianshi.songzeyang.service;

import com.tianshi.songzeyang.bean.User;

import java.util.List;

public interface UserService
{
    boolean register(User user);

    User login(String username, String password);

    boolean removeUserById(Integer id);

    boolean removeUsersByIds(List<Integer> ids);

    boolean modifyUser(User user);

    List<User> selectAll();

    List<User> queryUserByUsernameLike(String username);

    User queryUserByCondition(User user);

    boolean checkUsernameExist(String username);

    boolean verifyUsernameAndPassword(String username, String password);
}
