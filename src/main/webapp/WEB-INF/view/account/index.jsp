<%--
  Created by IntelliJ IDEA.
  User: bobzbfeng
  Date: 2018/6/5
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglib-header.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Index</title>
</head>
<body>

<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-pills nav-stacked" id="mainMenu">
        <tags:account_left_menu active="1"/>
    </ul>
</div>

<h3>欢迎用户 ${SPRING_SECURITY_CONTEXT.authentication.principal.username}登录!</h3>

</body>
</html>

