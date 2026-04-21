package com.tianshi.songzeyang.mapper;

import com.tianshi.songzeyang.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper
{
    // 新增用户
    int insert(User user);

    // 根据ID删除用户
    int deleteById(Integer id);

    // 多条件更新
    int update(User user);

    // 查询所有用户
    List<User> selectAll();

    // 用户名模糊查询
    List<User> selectByUsernameLike(String username);

    // 多条件查询
    User selectMultiCondition(User user);
}
