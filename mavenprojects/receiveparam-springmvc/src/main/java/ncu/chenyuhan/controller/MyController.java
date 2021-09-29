package ncu.chenyuhan.controller;

import ncu.chenyuhan.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Controller:创建处理器对象，对象放在spirngmvc容器中
 *     能处理请求的都是控制器（处理器）：MyController能处理请求，叫做后端控制器(back controller)
 */

@Controller
public class MyController {
    /**
     *逐个接收请求参数
     *   要求：处理器方法的形参名和请求中参数名必须一致
     *          同名的请求参数赋值给同名的形参
     */

    /**
     *逐个接收请求参数： 要求：处理器(控制器)方法的形参名和请求中参数名必须一致
     *                         同名的请求参数赋值给同名的形参
     *框架接收请求参数：1.使用request对象接收请求参数
     *                  String strName=request.getParameter("name");
     *                  String strAge=request.getParameter("age");
     *   2.springmvc框架通过DispatcherServlet 调用MyController的doSome()方法
     *    调用方法时，按名称对应，把接收的参数赋值给形参
     *    doSome(strName,Integer.valueOf(strAge))
     *    框架会提供类型转换的功能，能把String转为int,long,float,double等类型
     *
     *    400状态码是客户端错误，表示提交请求参数的过程中，发生了问题
     */
    @RequestMapping(value ="/receiveproperty.do")  //"/"表示根地址(项目的根)(即:http://localhost:8080/hello_springmvc_war_exploded/)
     public ModelAndView doSome(String name,int age) {  //doGet()--service请求处理
        //可以在方法中直接使用name,age
        //处理some.do请求。 相当于service调用处理完成了
        ModelAndView mv=new ModelAndView();
        mv.addObject("myname",name);
        mv.addObject("myage",age);

        //当配置了视图解析器后，可以使用逻辑名称(文件名),指定视图
        //框架会使用视图解析器的前缀+逻辑名称+后缀  组成完整路径，这里就是字符连接操作
        //   /WEB-INF/view/+show+.jsp

        mv.setViewName("show");
        return mv;
     }

    /**
     *请求中参数名和处理器方法的形参名不一样
     * @RequestParam:逐个接收请求参数中，解决请求中参数名形参名不一样的问题
     *   属性：1.value  请求中的参数名称
     *        2.required 是一个boolean，默认是true
     *          true:表示请求中必须包含此参数
     *       位置：在处理器方法的形参定义的前面
     */
    @RequestMapping(value ="/receiveparam.do")  //"/"表示根地址(项目的根)(即:http://localhost:8080/hello_springmvc_war_exploded/)
    public ModelAndView receiveParam(@RequestParam(value = "rname",required = false) String name,
                                     @RequestParam(value = "rage",required = false) Integer age) {  //doGet()--service请求处理
        //可以在方法中直接使用name,age
        //处理some.do请求。 相当于service调用处理完成了
        ModelAndView mv=new ModelAndView();
        mv.addObject("myname",name);
        mv.addObject("myage",age);

        //当配置了视图解析器后，可以使用逻辑名称(文件名),指定视图
        //框架会使用视图解析器的前缀+逻辑名称+后缀  组成完整路径，这里就是字符连接操作
        //   /WEB-INF/view/+show+.jsp

        mv.setViewName("show");
        return mv;
    }

    /**
     *处理器方法形参是java对象，这个对象的属性名和请求中参数名一样
     * 框架会创建形参的java对象，给属性赋值。请求中的参数是name,框架会调用setName()
     */
    @RequestMapping(value ="/receiveobject.do")  //"/"表示根地址(项目的根)(即:http://localhost:8080/hello_springmvc_war_exploded/)
    public ModelAndView receiveParam(Student myStudent) {  //doGet()--service请求处理
        System.out.println("receiveParam,name="+myStudent.getName()+"   age="+myStudent.getAge());
        //可以在方法中直接使用name,age
        //处理some.do请求。 相当于service调用处理完成了
        ModelAndView mv=new ModelAndView();
        mv.addObject("myname",myStudent.getName());
        mv.addObject("myage",myStudent.getAge());
        mv.addObject("mystudent",myStudent);

        //当配置了视图解析器后，可以使用逻辑名称(文件名),指定视图
        //框架会使用视图解析器的前缀+逻辑名称+后缀  组成完整路径，这里就是字符连接操作
        //   /WEB-INF/view/+show+.jsp

        mv.setViewName("show");
        return mv;
    }
}
