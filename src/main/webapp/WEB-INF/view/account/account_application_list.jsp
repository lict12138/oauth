<%--
  Created by IntelliJ IDEA.
  User: bobzbfeng
  Date: 2018/6/7
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglib-header.jsp" %>

<html>
<head>
    <title>应用列表</title>
</head>
<body>

<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-pills nav-stacked" id="mainMenu">
        <tags:account_left_menu active="2"/>
    </ul>
</div>

<div class="col-md-10">

    <h3>应用管理页面</h3>
    <hr>

    <div class="pull-right">
        <a class="btn btn-success" href="create/form">添加应用</a>
    </div>

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

        <dis:column property="createTime" title="创建时间"/>
        <dis:column property="description" title="备注"/>

        <dis:column title="操作">
            <c:if test="${d.status eq 'APPROVE'}">
                <a title="获取密钥的凭据" appUuid="${d.uuid}" class="btn btn-group client_details_button">凭据</a>
            </c:if>
        </dis:column>

    </dis:table>
</div>

<%--Client Details modal--%>
<div class="modal fade" id="appClientDetails" tabindex="-1" role="dialog" aria-labelledby="appClientDetailsModalLabel"
     style="over-flow:auto;">
    <div class="modal-dialog" role="document" id="modalSizeDiv">
        <div class="modal-content">
            <div class="modal-header clearfix">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="appClientDetailsModalLabel"></h4>
            </div>
            <div class="modal-body clearfix">
                <div id="appClientDetailsModalBody">
                </div>
            </div>
            <div class="modal-footer clearfix">
                <span id="modalMsg"></span>&nbsp;
                <button type="button" class="btn btn-success" id="appClientDetailsModalSubmit">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="appClientDetailsModalClose">关闭</button>
            </div>
        </div>
    </div>
</div>

<script src="${contextPath}/static/js/account_v1.js"></script>
<script>
    $(function () {
        new Account_Application();
    });

</script>


</body>
</html>
