<%--
  Created by IntelliJ IDEA.
  User: bobzbfeng
  Date: 2018/6/6
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglib-header.jsp" %>
<html>
<head>
    <title>账号管理</title>
</head>
<body>

<div class="row">

    <div  class="col-sm-3 col-md-2 sidebar">
        <ul class="nav nav-pills nav-stacked" id="mainMenu">
            <tags:system_root active="2"/>
        </ul>
    </div>


    <div class="col-md-10">

        <h3>账户管理页面</h3>
        <hr>

        <form action="" class="form-inline" id="filterForm">
            <div class="form-group">
                <input type="text" class="form-control" id="username" placeholder="用户名"
                       name="username"
                       value="${paginated.username}"/>
            </div>
            <button type="submit" class="btn btn-default"><em class="glyphicon glyphicon-search"></em></button>
            &nbsp;<span class="text-info">共: ${paginated.totalSize} 个用户</span>
        </form>
        <br/>

        <dis:table list="${paginated}" id="d" form="filterForm" class="table table-striped table-hover">
            <dis:column property="username" title="用户名"/>

            <dis:column title="状态">
                ${d.accountStatus.label}
            </dis:column>

            <dis:column property="createTime" title="创建时间"/>

            <dis:column title="操作">
                <c:if test="${d.accountStatus eq 'SUBMIT'}">
                    <a class="btn btn-success" href="${contextPath}/system/accounts/approve/page_${d.uuid}">审核</a>
                </c:if>
            </dis:column>

        </dis:table>
    </div>

</div>


</body>
</html>
