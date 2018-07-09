<%--
 * 
 * @author bobzbfeng
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ include file="../commons/taglib-header.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
    <script>
        <%--JS gloable varilible--%>
        var contextPath = "${contextPath}";
    </script>


    <meta name="viewport" content="width=device-width,user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="${_csrf.headerName}" content="${_csrf.token}"/>
    <link rel="shortcut icon" href="${contextPath}/static/favicon.ico"/>

    <title><decorator:title default=""/> | IoT认证中心</title>

    <link href="${contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <decorator:head/>

</head>
<body>
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="javascript:void(0)"><img alt="Brand" style="max-height: 25px;"
                                                                src="${contextPath}/static/images/logo_v2.png"></a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <p class="navbar-text">IoT认证中心</p>

            <form action="${contextPath}/signout" class="navbar-form navbar-right" role="search" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-danger">Logout</button>
            </form>
            <p class="navbar-text pull-right"><a
                    href="">${SPRING_SECURITY_CONTEXT.authentication.principal.username}</a></p>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <%--JS--%>
    <script src="${contextPath}/static/js/jquery.min.js"></script>
    <script src="${contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="${contextPath}/static/js/system_root.js"></script>
    <decorator:body/>

   <tags:foot/>
</div>

</body>
</html>