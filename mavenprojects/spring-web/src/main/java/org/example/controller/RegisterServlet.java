package org.example.controller;

import org.example.domain.Student;
import org.example.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId=request.getParameter("id");
        String strName=request.getParameter("name");
        String strEmail=request.getParameter("email");
        String strAge=request.getParameter("age");

        //创建spring的容器对象
        //String config= "applicationContextRenamed.xml";
        //ApplicationContext ctx=new ClassPathXmlApplicationContext(config);

        WebApplicationContext ctx=null;
        /*
        //获取ServletContext中的容器对象，创建好的容器对象，拿来就用
        String key=WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;
        Object attr=getServletContext().getAttribute(key);
        if (attr!=null){
            ctx=(WebApplicationContext) attr;
        }*/

        //使用框架中的方法，获取容器对象
        ServletContext sc=getServletContext();
        ctx= WebApplicationContextUtils.getRequiredWebApplicationContext(sc);

        System.out.println("容器对象的信息======"+ctx);

        //获取service
        StudentService service=(StudentService) ctx.getBean("studentService");
        Student student=new Student(Integer.parseInt(strId),strName,strEmail
                             ,Integer.valueOf(strAge));
        service.addStudent(student);

        //给一个页面
        request.getRequestDispatcher("/result.jsp").forward(request,response);

    }
}
