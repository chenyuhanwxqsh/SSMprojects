<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
</head>
<body>

<p>当出来方法返回ModelAndView实现forward</p>
<form action="doForward.do" method="post">
    姓名：<input type="text" name="name"> <br/>
    年龄：<input type="text" name="age"> <br/>
    <input type="submit" value="提交请求">
</form>

<br/>
<br/>
<p>当出来方法返回ModelAndView实现redirect</p>
<form action="doRedirect.do" method="post">
    姓名：<input type="text" name="name"> <br/>
    年龄：<input type="text" name="age"> <br/>
    <input type="submit" value="提交请求">
</form>

<br/>
<img src="images/p4.jpg" alt="是一个静态资源，不能显示">
</body>
</html>
