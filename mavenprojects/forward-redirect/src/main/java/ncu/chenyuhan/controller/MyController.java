package ncu.chenyuhan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    /**
     *处理器方法返回ModelAndView,实现转发forward
     * 语法：setViewName("forward:视图文件完整路径")
     * forward特点：不和视图解析器一同时用，就当项目中没有视图解析器
     */
    @RequestMapping(value ="/doForward.do")
     public ModelAndView doSome() {

        ModelAndView mv=new ModelAndView();
        mv.addObject("msg","欢迎使用");
        mv.addObject("fun","pinming");
        mv.setViewName("forward:/WEB-INF/view/show.jsp");
        //如果当文件没有在视图解析器设置的路径时，就可以使用forward
        //mv.setViewName("forward:/hello.jsp");
        return mv;
     }

    /**
     *处理器方法返回ModelAndView,实现重定向redirect
     * 语法：setViewName("redirect:视图文件完整路径")
     * redirect特点：不和视图解析器一同时用，就当项目中没有视图解析器
     *
     * 框架对重定向的操作：
     * 1.框架会把Model中的简单类型的数据，转为string使用，作为hello.jsp的get请求参数使用
     *    目的是在doRedirect.do和hello.jsp俩次请求之间传递数据
     * 2.在目标hello.jsp页面可以使用参数集合对象${param}获取请求参数值
     *    ${param.myname}
     * 3.重定向不能访问/WEN-INF资源
     */
    @RequestMapping(value ="/doRedirect.do")
    public ModelAndView doWithRedirect(String name,Integer age) {

        ModelAndView mv=new ModelAndView();
        //数据放入到request作用域
        mv.addObject("myname",name);
        mv.addObject("myage",age);
        //重定向
        mv.setViewName("redirect:/hello.jsp");
        //http://localhost:8080/forward_redirect_war_exploded/hello.jsp?myname=%E8%96%9B&myage=3
        return mv;
    }

}
