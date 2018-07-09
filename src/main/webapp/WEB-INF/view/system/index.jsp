<%--
 * 
 * @author bobzbfeng
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglib-header.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>首页</title>
</head>
<body>

<div id="click" class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-pills nav-stacked" id="mainMenu">
        <tags:system_root active="1"/>
    </ul>
</div>

<h3>欢迎管理员 ${SPRING_SECURITY_CONTEXT.authentication.principal.username}登录!</h3>

</body>
</html>
