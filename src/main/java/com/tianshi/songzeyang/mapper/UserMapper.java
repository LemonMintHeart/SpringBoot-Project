package com.tianshi.songzeyang.mapper;

import com.tianshi.songzeyang.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param; // 【新增】导入注解
import java.util.List;

@Mapper
public interface UserMapper
{
    // 新增用户
    int insert(@Param("user") User user);

    // 根据ID删除用户
    int deleteById(@Param("id") Integer id);

    // 多条件更新
    int update(@Param("user") User user);

    // 查询所有用户
    List<User> selectAll();

    // 用户名模糊查询
    List<User> selectByUsernameLike(@Param("username") String username);

    // 多条件查询
    User selectMultiCondition(@Param("user") User user);
}