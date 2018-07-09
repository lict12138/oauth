<%--
  Created by IntelliJ IDEA.
  User: bobzbfeng
  Date: 2018/6/7
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>

    <meta charset="utf-8"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>

    <meta name="viewport" content="width=device-width,user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="${_csrf.headerName}" content="${_csrf.token}"/>
    <link rel="shortcut icon" href="${contextPath}/static/favicon.ico"/>

    <title>注册成功 | IoT认证中心</title>

    <link href="${contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>


<div class="row">

    <div class="container">
        <div class="col-md-12">
            <h3 class="text-left">注册成功</h3>
            <hr>

            <div class="alert alert-success text-center" role="alert">
                <h3>注册信息已成功提交，请耐心等待管理员审核!</h3>

                <p>
                    注册账号: <strong>${name}</strong>
                </p>

                <p>
                     <a href="${contextPath}/">返回</a>.
                </p>
            </div>
        </div>
    </div>
</div>


</body>
</html>
