package com.tianshi.songzeyang.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.tianshi.songzeyang.bean.User;
import com.tianshi.songzeyang.service.UserService;
import com.tianshi.songzeyang.util.MailUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private MailUtil mailUtil;

    // 非空校验工具
    private boolean isBlank(String str)
    {
        return str == null || str.trim().isEmpty();
    }

    // 图片验证码生成
    @GetMapping("/getCode")
    public void getCode(HttpSession session, HttpServletResponse response) throws IOException
    {
        // 禁用 ImageIO 的磁盘缓存（避免 temp 目录写入权限问题）
        ImageIO.setUseCache(false);
        // 1. 创建验证码：宽120px、高40px、4位字符、10条干扰线
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(120, 40, 4, 10);
        // 2. 验证码字符串存入Session
        session.setAttribute("imgCode", lineCaptcha.getCode());
        // 3. 禁止缓存（防止验证码图片不刷新）
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 4. 输出验证码图片到响应流
        lineCaptcha.write(response.getOutputStream());
    }

    // 发送邮箱验证码
    @PostMapping("/sendEmailCode")
    @ResponseBody
    public void sendEmailCode(@RequestParam("email") String email, HttpSession session, HttpServletResponse response) throws IOException {
        // 1. 生成6位验证码
        String emailCode = MailUtil.generateEmailCode();
        // 2. 存入Session（设置5分钟有效期）
        session.setAttribute("emailCode", emailCode);
        session.setAttribute("emailCodeExpire", System.currentTimeMillis() + 5 * 60 * 1000);
        // 3. 发送验证码
        boolean isSuccess = mailUtil.sendEmailCode(email, emailCode);
        // 4. 返回结果
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().write(isSuccess ? "success" : "fail");
    }

    // 去注册页
    @GetMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    // 去登录页
    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           @RequestParam String email,
                           @RequestParam String emailCode,
                           HttpSession session)
    {
        // 1. 参数完整性校验
        if (isBlank(username) || isBlank(password) || isBlank(confirmPassword)) {
            return "register";
        }
        if (!password.equals(confirmPassword)) {
            return "register";
        }

        String sessionCode = (String) session.getAttribute("emailCode");
        Long expireTime = (Long) session.getAttribute("emailCodeExpire");
        if (sessionCode == null || expireTime == null || System.currentTimeMillis() > expireTime) {
            return "register";
        }
        if (!sessionCode.equalsIgnoreCase(emailCode)) {
            return "register";
        }

        // 2. 先调用Service检测用户名是否存在
        if (userService.checkUsernameExist(username)) {
            return "register";
        }

        // 3. 检测通过，再调用Service执行注册
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        boolean flag = userService.register(user);

        if (flag) {
            session.removeAttribute("emailCode");
            session.removeAttribute("emailCodeExpire");
            return "login";
        } else {
            return "register";
        }
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public String login(HttpSession httpSession,
                        @RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String verifyCode)
    {
        // 1. 参数完整性校验
        if (isBlank(username) || isBlank(password) || isBlank(verifyCode)) {
            return "login";
        }

        String sessionCode = (String) httpSession.getAttribute("imgCode");
        if(sessionCode == null || !sessionCode.equalsIgnoreCase(verifyCode)){
            return "login";
        }

        // 2. 先调用Service验证账号密码是否匹配
        if (!userService.verifyUsernameAndPassword(username, password)) {
            return "login";
        }

        // 3. 验证通过，再调用Service执行登录（获取用户信息）
        User user = userService.login(username, password);
        if (user != null) {
            httpSession.setAttribute("loginUser", user);
            return "redirect:/index.jsp";
        } else {
            return "login";
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
            return "login";
        } else {
            httpSession.removeAttribute("loginUser");
            return "login";
        }
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
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
     * 批量删除用户
     */
    @DeleteMapping("/delete/batch")
    @ResponseBody
    public String deleteBatch(@RequestParam List<Integer> ids)
    {
        if (ids == null || ids.isEmpty()) {
            return "批量删除失败！原因：ID列表不能为空";
        }
        boolean flag = userService.removeUsersByIds(ids);
        if (flag) {
            return "批量删除成功！";
        } else {
            return "批量删除失败！未知原因！";
        }
    }

    /**
     * 更新用户
     */
    @PutMapping("/update/{id}")
    @ResponseBody
    public String update(@PathVariable("id") Integer id,
                         @RequestBody User user)
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
    @ResponseBody
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
    @ResponseBody
    public User queryCondition(User user) {
        return userService.queryUserByCondition(user);
    }
}