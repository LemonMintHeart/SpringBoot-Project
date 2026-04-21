package com.tianshi.songzeyang.controller;

import com.tianshi.songzeyang.bean.User;
import com.tianshi.songzeyang.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    // 非空校验工具
    private boolean isBlank(String str)
    {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String confirmPassword)
    {
        // 1. 参数完整性校验
        if (isBlank(username) || isBlank(password) || isBlank(confirmPassword)) {
            return "注册失败！原因：用户名/密码/确认密码不能为空";
        }
        if (!password.equals(confirmPassword)) {
            return "注册失败！原因：两次密码不一致";
        }

        // 2. 先调用Service检测用户名是否存在
        if (userService.checkUsernameExist(username)) {
            return "注册失败！原因：用户名已存在";
        }

        // 3. 检测通过，再调用Service执行注册
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean flag = userService.register(user);

        if (flag) {
            return "注册成功！";
        } else {
            return "注册失败！未知原因！";
        }
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public String login(HttpSession httpSession,
                        @RequestParam String username,
                        @RequestParam String password)
    {
        // 1. 参数完整性校验
        if (isBlank(username) || isBlank(password)) {
            return "登录失败！原因：用户名/密码不能为空";
        }

        // 2. 先调用Service验证账号密码是否匹配
        if (!userService.verifyUsernameAndPassword(username, password)) {
            return "登录失败！原因：用户名或密码错误";
        }

        // 3. 验证通过，再调用Service执行登录（获取用户信息）
        User user = userService.login(username, password);
        if (user != null) {
            httpSession.setAttribute("loginUser", user);
            return "登录成功！";
        } else {
            return "登录失败！未知原因！";
        }
    }

    /**
     * 用户登出
     */
    @GetMapping("/logout")
    public String logout(HttpSession httpSession)
    {
        User user = (User) httpSession.getAttribute("loginUser");

        if (user == null) {
            return "登出失败！原因：用户未登录";
        } else {
            httpSession.removeAttribute("loginUser");
            return "登出成功！";
        }
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id)
    {
        if (id == null) return "删除失败！原因：用户ID为空";

        boolean flag = userService.removeUserById(id);
        if (flag) {
            return "删除成功！";
        } else {
            return "删除失败！原因：用户不存在";
        }
    }

    /**
     * 更新用户
     */
    @PutMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id,
                         @RequestParam User user)
    {
        user.setId(id);

        if (user.getId() == null) return "更新失败！原因：用户ID不能为空";

        boolean flag = userService.modifyUser(user);
        if (flag) {
            return "更新成功！";
        } else {
            return "更新失败！原因：用户不存在";
        }
    }

    /**
     * 根据用户名模糊查询
     **/
    @GetMapping("/list")
    public List<User> list(@RequestParam(required = false) String username) {
        if (isBlank(username)) {
            // 无参数：查询所有用户
            return userService.selectAll();
        } else {
            // 有参数：按用户名模糊查询
            return userService.queryUserByUsernameLike(username);
        }
    }

    /**
     * 多条件查询
     **/
    @GetMapping("/condition")
    public User queryCondition(User user) {
        return userService.queryUserByCondition(user);
    }
}