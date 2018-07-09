<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="active" required="true" type="java.lang.Integer" %>

<div class="list-group">
    <a href="${contextPath}/account/index" class="list-group-item ${active eq '1'?'active':''}">首页</a>
    <a href="${contextPath}/account/application/list" class="list-group-item ${active eq '2'?'active':''}">应用</a>
    <a href="${contextPath}/account/document" class="list-group-item ${active eq '3'?'active':''}">接口文档</a>
</div>
