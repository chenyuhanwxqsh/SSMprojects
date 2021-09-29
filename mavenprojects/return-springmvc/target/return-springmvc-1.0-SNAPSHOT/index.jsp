<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(function (){
            $("button").click(function (){
                //alert("button click");
                $.ajax({
                    //url:"returnVoid-ajax.do",
                    //url:"returnStudentJson.do",
                    url:"returnStudentJsonArray.do",
                    data:{
                        name:"zhangsan",
                        age:20
                    },
                    type:"post",
                    //dataType:"json",    加也行不加也行，因为服务器端已经  response.setContentType("application/json;charset=utf-8");
                    success:function (resp){
                        //resp从服务器端返回的是json格式的字符串 {"name":"zhangsan","age":20}
                        //jquery会把字符串转为json对象，赋值给resp形参

                        //alert(resp.name+"    "+resp.age);//后端输出的json就会被传到前端jsp的ajax请求中的resp中，
                                    //拿到的是一个字符串，当jquery拿到它后，会将它转化为json的Object进行使用

                        $.each(resp,function (i,n){
                            alert(n.name+"   "+n.age)
                        })
                    }
                })
            })
        })
    </script>
</head>
<body>

    <p>处理器方法返回String表示视图名称</p>
    <form action="returnString-view.do" method="post">
        姓名：<input type="text" name="name"> <br/>
        年龄：<input type="text" name="age"> <br/>
        <input type="submit" value="提交参数">
    </form>

    <br/>
    <br/>
    <button id="btn">发起ajax请求</button>

</body>
</html>
