<%--
  Created by IntelliJ IDEA.
  User: bobzbfeng
  Date: 2018/6/7
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglib-header.jsp" %>
<html>
<head>
    <title>应用管理</title>
</head>
<body>

<div class="row">

    <div  class="col-sm-3 col-md-2 sidebar">
        <ul class="nav nav-pills nav-stacked" id="mainMenu">
            <tags:system_root active="3"/>
        </ul>
    </div>


    <div class="col-md-10">

        <h3>账户管理页面</h3>
        <hr>

        <form action="" class="form-inline" id="filterForm">
            <div class="form-group">
                <input type="text" class="form-control" id="applicationName" placeholder="应用名称"
                       name="applicationName"
                       value="${paginated.applicationName}"/>
            </div>
            <button type="submit" class="btn btn-default"><em class="glyphicon glyphicon-search"></em></button>
            &nbsp;<span class="text-info">共: ${paginated.totalSize} 个应用</span>
        </form>
        <br/>

        <dis:table list="${paginated}" id="d" form="filterForm" class="table table-striped table-hover">
            <dis:column property="applicationName" title="应用名称"/>

            <dis:column title="状态">
                ${d.status.label}
            </dis:column>

            <dis:column property="creatorName" title="创建者"/>
            <dis:column property="createTime" title="创建时间"/>
            <dis:column property="description" title="备注"/>

            <dis:column title="操作">
                <c:if test="${d.status eq 'SUBMIT'}">
                    <a class="btn btn-success" href="${contextPath}/system/application/approve/page_${d.uuid}">审核</a>
                </c:if>
            </dis:column>

        </dis:table>
    </div>

</div>

</body>
</html>
