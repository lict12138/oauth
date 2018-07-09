<%--
  Created by IntelliJ IDEA.
  User: bobzbfeng
  Date: 2018/6/5
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<!DOCTYPE HTML>
<html>
<head>

    <meta charset="utf-8"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>

    <meta name="viewport" content="width=device-width,user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="${_csrf.headerName}" content="${_csrf.token}"/>
    <link rel="shortcut icon" href="${contextPath}/static/favicon.ico"/>

    <title>账号注册 |  IoT认证中心</title>

    <link href="${contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>

<div class="row">

    <div class="container">
        <div class="col-md-12">


            <h3 class="text-left">账号注册</h3>
            <hr>

            <form:form commandName="formDto" cssClass="form-horizontal">

                <div class="form-group">
                    <label class="control-label col-sm-2">用户名(登录账号)<em class="text-danger">*</em></label>

                    <div class="col-sm-10">
                        <form:input path="username" cssClass="form-control" required="true" placeholder="用户名"/>
                        <form:errors path="username" cssClass="label label-warning"/>
                        <p class="help-block">用户名,必填,长度大于等于8位</p>
                    </div>
                </div>


                <div class="form-group">
                    <label class="control-label col-sm-2">登录密码<em class="text-danger">*</em></label>

                    <div class="col-sm-10">
                        <form:password path="password" cssClass="form-control" required="true" placeholder="系统登录密码"/>
                        <form:errors path="password" cssClass="label label-warning"/>
                        <p class="help-block">系统登录密码,长度大于等于8位</p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2">确认密码<em class="text-danger">*</em></label>

                    <div class="col-sm-10">
                        <form:password path="rePassword" cssClass="form-control" required="true" placeholder="确认密码"/>
                        <form:errors path="rePassword" cssClass="label label-warning"/>
                        <p class="help-block">确认密码,长度大于等于8位</p>
                    </div>
                </div>


                <div class="form-group">
                    <label class="control-label col-sm-2"></label>

                    <div class="col-sm-10">
                        <c:if test="${not empty alert}">
                            <p class="label label-warning">${alert}</p>
                        </c:if>
                        <button class="btn btn-success" type="submit">注册</button>
                        &nbsp;<a href="${contextPath}/login">返回</a>
                    </div>
                </div>

            </form:form>

            <tags:foot/>
        </div>
    </div>
</div>

</body>
</html>
