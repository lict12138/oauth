<%--
  Created by IntelliJ IDEA.
  User: bobzbfeng
  Date: 2018/6/21
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglib-header.jsp" %>
<html>
<head>
    <title>接口文档</title>

    <style>
        body {
            font-family: "微软雅黑", Helvetica, Verdana, Arial, sans-serif;
        }
    </style>

</head>
<body>

<div class="row">

    <div  class="col-sm-3 col-md-2 sidebar">
        <ul class="nav nav-pills nav-stacked" id="mainMenu">
            <tags:account_left_menu active="3"/>
        </ul>
    </div>


    <div class="col-md-10">

        <h3>接口文档</h3>
        <hr>

        <div class="alert alert-info">
            <strong>注意</strong>: 所有API请求都需要
            <mark>access_token</mark>
            参数 除了标记<span class="badge">public</span> API, 若没有
            <mark>access_token</mark>
            参数, 服务端将响应HTTP状态码 401
            <br/>

        </div>


        <%--验签，加密，解密--%>
        <div class="well well-sm">
            <h3>验签，加密，解密
            </h3>

            <p class="text-info">该接口用于处理 验签，加密，解密</p>

            <ul class="list-group">
                <li class="list-group-item">
                    <p>
                        请求URI:
                        <br/>
                        <code>/api/application/crypt</code> <span class="label label-warning">POST</span>
                        <span class="label label-success">REST</span>
                    </p>
                    <p>
                        Content-Type:
                        <mark>application/json</mark>
                    </p>

                    <div>
                        请求参数:
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>参数名</th>
                                <th>参数值</th>
                                <th>备注</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>hid</td>
                                <td>String</td>
                                <td>硬件对应的唯一ID</td>
                            </tr>
                            <tr>
                                <td>result</td>
                                <td>String</td>
                                <td>result</td>
                            </tr>
                            <tr>
                                <td>data</td>
                                <td>String</td>
                                <td>data</td>
                            </tr>
                            <tr>
                                <td>type</td>
                                <td>Integer</td>
                                <td>0代表验签，1代表加密，2代表解密</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <br/>
                    <strong>验签请求示例:</strong>
                     <pre>
{
  "hid":"0186171200000024",
  "data":"11",
  "result":"00000009C9F80520661330506860D51DA26E6A312A934C5A4D48886A3C0054C32D84EFACB53A3929AFDAF0AC7AEB3AB01189D750F55D662DE7D16CC9A79B2C8DFF661C21",
  "type":0
}
                    </pre>
                    <br/>

                    <strong>加密请求示例:</strong>
                     <pre>
{
  "hid":"0186171200000024",
  "data":"11223344556677",
  "type":1
}
                    </pre>
                    <br/>

                    <strong>解密请求示例:</strong>
                     <pre>
{
  "hid":"0186171200000024",
  "data":"2179E3676B2A3A19EED8AF7C0E9DD98BF5DE0A71",
  "type":2
}
                    </pre>
                    <br/>
                    <strong>响应:</strong>
                    <pre>
                        {"err":-1,data:""}
                    </pre>
                    <p class="help-block">err 对应错误码，0代表请求成功，其余暂无匹配 </p>
                    <p class="help-block">请求失败时，data 对应错误内容；请求失败时，data对应返回数据 </p>
                </li>
            </ul>

        </div>


        <div class="well well-sm" id="applicationApi">
            <h3>Token URL</h3>

            <p class="text-info"> 调用该API，成功返回access_token</p>
            <code>/oauth/token?client_id=xxxxxxxx&client_secret=xxxxxxxxx&scope=read&grant_type=client_credentials</code>
            <span class="label label-warning">POST</span>
        </div>
    </div>

</div>





</body>
</html>
