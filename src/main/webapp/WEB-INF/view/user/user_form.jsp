<%--
  Created by IntelliJ IDEA.
  User: Shaofei Du.
  Date: 2016/6/02
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglib-header.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>添加账户</title>
    <meta charset="utf-8"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>

    <meta name="viewport" content="width=device-width,user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="${_csrf.headerName}" content="${_csrf.token}"/>

    <link href="${contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>

<div class="row">

    <div class="container" style="margin-top:-100px;">


        <div class="col-md-12">

            <h3 class="text-left">注册开发者帐号</h3>
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
                    <label class="control-label col-lg-2">权限<em
                            class="text-danger">*</em>
                    </label>

                    <div class="col-sm-10">
                        <form:radiobuttons path="privileges" items="${systemUserPrivileges}" itemValue="value"
                                           itemLabel="label" delimiter="<br/>"/>
                            <%--<form:checkboxes path="privileges" checked="checked" items="${systemUserPrivileges}"
                                             itemValue="value" itemLabel="label" delimiter="<br/>" />--%>

                        <form:errors path="privileges" cssClass="label label-warning"/>
                        <p class="help-block">社区活动权限</p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2"></label>

                    <div class="col-sm-10">
                        <c:if test="${not empty alert}">
                            <p class="label label-warning">${alert}</p>
                        </c:if>
                        <button class="btn btn-success" type="submit">保存</button>
                        &nbsp;<a href="${contextPath}/root/people/list">返回</a>
                    </div>
                </div>

            </form:form>
        </div>

    </div>
</div>

</body>
</html>
