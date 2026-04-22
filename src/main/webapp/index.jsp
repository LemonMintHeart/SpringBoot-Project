<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
</head>
<body>
<%
    Object loginUser = session.getAttribute("loginUser");
    if (loginUser != null) {
%>
<h1>欢迎！<%= ((com.tianshi.songzeyang.bean.User)loginUser).getUsername() %>！</h1>
<a href="${pageContext.request.contextPath}/user/logout">退出登录</a>
<%
} else {
%>
<h1>您尚未登录！</h1>
<a href="${pageContext.request.contextPath}/user/toLogin">去登录</a>
<%
    }
%>
</body>
</html>