<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>注册</title>
</head>
<body>
<h1>用户注册</h1>

<!-- 注册表单 -->
<form action="${pageContext.request.contextPath}/user/register" method="post">
    <div>
        <label>用户名：</label>
        <input type="text" name="username" required>
    </div>
    <div>
        <label>密码：</label>
        <input type="password" name="password" required>
    </div>
    <div>
        <label>确认密码：</label>
        <input type="password" name="confirmPassword" required>
    </div>
    <div>
        <label>邮箱：</label>
        <input type="email" name="email" id="email" required>
        <button type="button" onclick="sendEmailCode()">发送验证码</button>
    </div>
    <div>
        <label>邮箱验证码：</label>
        <input type="text" name="emailCode" required>
    </div>
    <div>
        <button type="submit">注册</button>
        <a href="${pageContext.request.contextPath}/user/toLogin">已有账号？去登录</a>
    </div>
</form>

<!-- 发送邮箱验证码的JS -->
<script>
    function sendEmailCode() {
        const email = document.getElementById("email").value;
        if (!email) {
            alert("请先输入邮箱！");
            return;
        }

        // 创建XMLHttpRequest对象发送POST请求
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "${pageContext.request.contextPath}/user/sendEmailCode", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                if (xhr.responseText === "success") {
                    alert("验证码发送成功！请查收邮箱");
                } else {
                    alert("验证码发送失败！请检查邮箱是否正确");
                }
            }
        };

        xhr.send("email=" + encodeURIComponent(email));
    }
</script>
</body>
</html>