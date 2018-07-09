<%--
  Created by IntelliJ IDEA.
  User: bobzbfeng
  Date: 2018/6/8
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul class="list-group">
    <li class="list-group-item "><span
            class="text-muted">应用名称: </span> ${dto.applicationName}</li>

    <li class="list-group-item list-group-item-info"><span
            class="text-muted"><strong>clientId</strong>: </span><code>${dto.clientId}</code></li>

    <li class="list-group-item list-group-item-info"><span
            class="text-muted"><strong>clientSecret</strong>: </span><code>${dto.clientSecret}</code></li>


    <li class="list-group-item list-group-item-info"><span
            class="text-muted"><strong>token有效期</strong>: </span>
        <label>
            <input type="text" name="validTime" id="validTime" value="${dto.validTime}"/>
            <input type="hidden" id="clientId" value="${dto.clientId}">
        </label>小时</li>
</ul>
