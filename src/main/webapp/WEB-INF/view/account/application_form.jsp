<%--
  Created by IntelliJ IDEA.
  User: bobzbfeng
  Date: 2018/6/7
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglib-header.jsp" %>
<html>
<head>
    <title>添加应用</title>
</head>
<body>

<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-pills nav-stacked" id="mainMenu">
        <tags:account_left_menu active="2"/>
    </ul>
</div>
<div class="col-md-10">

    <h3 class="text-left">添加应用</h3>
    <hr>

    <form:form commandName="formDto" cssClass="form-horizontal">

        <div class="form-group">
            <label class="control-label col-sm-2">应用名称<em class="text-danger">*</em></label>

            <div class="col-sm-10">
                <form:input path="applicationName" cssClass="form-control" required="true" placeholder="应用名称"/>
                <form:errors path="applicationName" cssClass="label label-warning"/>
                <p class="help-block">应用名称</p>
            </div>
        </div>


        <div class="form-group">
            <label class="control-label col-sm-2">备注</label>

            <div class="col-sm-10">
                <form:textarea path="description" cssClass="form-control" placeholder="应用备注"/>
                <form:errors path="description" cssClass="label label-warning"/>
                <p class="help-block">应用的备注信息，可选</p>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2"></label>

            <div class="col-sm-10">
                <button class="btn btn-success" type="submit">添加</button>
                &nbsp;<a href="${contextPath}/account/application/list">返回</a>
            </div>
        </div>
    </form:form>

</div>



</body>
</html>
