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
            <tags:system_root active="4"/>
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


         <%--SE注册接口--%>
        <div class="well well-sm">
                <h3>注册SE
                </h3>

                <p class="text-info">注册SE，获取密钥,该API供 TUSI 认证中心调用</p>

                <ul class="list-group">
                    <li class="list-group-item">
                        <p>
                            请求URI:
                            <br/>
                            <code>/api/register/action</code> <span class="label label-warning">POST</span>
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
                                    <td>rsapubkey</td>
                                    <td>String</td>
                                    <td>rsapubkey</td>
                                </tr>
                                <tr>
                                    <td>eccpubkey</td>
                                    <td>String</td>
                                    <td>eccpubkey</td>
                                </tr>
                                <tr>
                                    <td>algType</td>
                                    <td>Integer</td>
                                    <td>algType</td>
                                </tr>
                                </tbody>
                            </table>
                            请求示例:
                        </div>
                        <br/>
                        <strong>响应:</strong>
                    <pre>
                        {"err":-1,"encryptedKey":"xxxxxxx","downCounter":"xxxxxx"}
                    </pre>
                        <p class="help-block">err 对应错误码，0代表请求成功，其余暂无匹配</p>
                    </li>
                </ul>

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
                    <strong>请求示例:</strong>
                     <pre>
                        {
  "hid":"0186171200000024",
  "data":"11",
  "result":"00000009C9F80520661330506860D51DA26E6A312A934C5A4D48886A3C0054C32D84EFACB53A3929AFDAF0AC7AEB3AB01189D750F55D662DE7D16CC9A79B2C8DFF661C21",
  "type":0
}
                    </pre>
                    <br/>
                    <strong>响应:</strong>
                    <pre>
                        {"err":-1,data:""}
                    </pre>
                    <p class="help-block">err 对应错误码，0代表请求成功，其余暂无匹配 </p>
                    <p class="help-block">data 对应错误内容 </p>
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
