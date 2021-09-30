<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2021/9/15
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    退出系统，从session中删除数据
<%
    session.removeAttribute("name");
%>
</body>
</html>
