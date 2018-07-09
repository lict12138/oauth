<%--
  Created by IntelliJ IDEA.
  User: bobzbfeng
  Date: 2018/6/7
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglib-header.jsp" %>
<html>
<head>
    <title>应用审核</title>
</head>
<body>

<div class="row">

    <div  class="col-sm-3 col-md-2 sidebar">
        <ul class="nav nav-pills nav-stacked" id="mainMenu">
            <tags:system_root active="2"/>
        </ul>
    </div>


    <div class="col-sm-9">

        <h3>应用审核</h3>
        <hr>

        <ul class="list-group">
            <li class="list-group-item list-group-item-info"><span
                    class="text-muted">应用名称: </span> ${dto.applicationName}

            <li class="list-group-item "><span
                    class="text-muted">备注: </span> ${dto.description}

            <li class="list-group-item list-group-item-info"><span
                    class="text-muted">创建者: </span> ${dto.creatorName}

            <li class="list-group-item"><span
                    class="text-muted">创建时间: </span> ${dto.createTime}
            </li>
        </ul>

        <div class="row">
            <div class="col-md-4">
                <form action="${contextPath}/system/application/approve_${dto.uuid}" method="post"
                      onsubmit="return confirm('确定通过应用 [${dto.applicationName}] 的审核吗')">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button type="submit" class="btn btn-default"><em class="glyphicon"></em>
                        通过
                    </button>
                    <p class="help-block">审核通过.</p>
                </form>
            </div>
            <div class="col-md-4">
                <form action="${contextPath}/system/accounts/reject_${dto.uuid}" method="post"
                      onsubmit="return confirm('确定拒绝应用 [${dto.applicationName}] 的审核吗')">
                    <button class="btn btn-warning" type="submit"><em class="glyphicon"></em>
                        不通过
                    </button>
                    <p class="help-block">审核不通过.</p>
                </form>
            </div>

        </div>
        <div class="text-center">
            <br/>
            <a class="btn btn-default" href="${contextPath}/system/application/list">返回</a>
        </div>

    </div>

</div>

</body>
</html>

