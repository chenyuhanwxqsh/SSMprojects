<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<h3>JokerXueforever</h3><br/>
<h3>myname数据：${param.myname}</h3><br/>
<h3>myage数据：${param.myage}</h3>
<h3>取参数数据：<%=request.getParameter("myname")%>></h3>

</body>
</html>
