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
    <title>系统配置</title>
    <meta charset="utf-8"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>

    <meta name="viewport" content="width=device-width,user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="${_csrf.headerName}" content="${_csrf.token}"/>
</head>
<body>


<div class="row">
    <div class="col-md-2">
        <tags:system_root active="1"/>
    </div>


    <div class="col-md-10">
        <form:form commandName="formDto" cssClass="form-horizontal">

            <div class="form-group">
                <label class="control-label col-sm-2">IDP SSO Callback URL<em class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <form:input path="ssoCallbackUrl" cssClass="form-control" required="true" placeholder="Type SSO callback url"/>
                    <form:errors path="ssoCallbackUrl" cssClass="label label-warning"/>
                    <p class="help-block">Sprinter support IDP sso, must config IDP sso callback url: /api/v1/pickup/userinfo/</p>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2">IDP SSO API Username<em class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <form:input path="ssoUsername" cssClass="form-control" required="true" placeholder="Type SSO username"/>
                    <form:errors path="ssoUsername" cssClass="label label-warning"/>
                    <p class="help-block">Sprinter support IDP sso, must config IDP sso username<</p>
                </div>
            </div>


            <div class="form-group">
                <label class="control-label col-sm-2">IDP SSO API Password<em class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <form:password path="ssoPassword" cssClass="form-control" required="true" placeholder="Type SSO password"/>
                    <form:errors path="ssoPassword" cssClass="label label-warning"/>
                    <p class="help-block">Sprinter support IDP sso, must config IDP sso password</p>
                </div>
            </div>


            <div class="form-group">
                <label class="control-label col-sm-2"></label>

                <div class="col-sm-10">
                    <c:if test="${alert eq 'success'}">
                        <label cssClass="label label-warning">保存成功</label>
                    </c:if>
                    <c:if test="${not empty alert}">
                        <p class="label label-warning">${alert}</p>
                    </c:if>
                    <button class="btn btn-success" type="submit">保存</button>
                    &nbsp;<a href="${contextPath}/">返回</a>
                </div>
            </div>

        </form:form>
    </div>
</div>

</body>
</html>
