<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h1>用户登录</h1>

<!-- 登录表单 -->
<form action="${pageContext.request.contextPath}/user/login" method="post">
    <div>
        <label>用户名：</label>
        <input type="text" name="username" required>
    </div>
    <div>
        <label>密码：</label>
        <input type="password" name="password" required>
    </div>
    <div>
        <label>验证码：</label>
        <input type="text" name="verifyCode" required style="width: 100px;">
        <!-- 验证码图片，点击刷新 -->
        <img src="${pageContext.request.contextPath}/user/getCode"
             onclick="this.src='${pageContext.request.contextPath}/user/getCode?'+Math.random()"
             title="点击刷新"
             style="vertical-align: middle;">
    </div>
    <div>
        <button type="submit">登录</button>
        <a href="${pageContext.request.contextPath}/user/toRegister">没有账号？去注册</a>
    </div>
</form>
</body>
</html>