<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2021/9/11
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>第一个springmvc项目</p>
    <p><a href="test/some.do">发起some.do的get请求</a></p>

    <br/>
    <form action="test/other.do" method="post">
        <input type="submit" value="post请求other.do">
    </form>
<%--    <p><a href="test/other.do">发起other.do的请求</a></p>--%>
</body>
</html>
