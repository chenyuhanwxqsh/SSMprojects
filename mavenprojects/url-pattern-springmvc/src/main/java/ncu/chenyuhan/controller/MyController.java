package ncu.chenyuhan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Controller:创建处理器对象，对象放在spirngmvc容器中
 *     能处理请求的都是控制器（处理器）：MyController能处理请求，叫做后端控制器(back controller)
 */

@Controller
public class MyController {

    @RequestMapping(value ="/some.do")
     public ModelAndView doSome(String name, int age) {
        System.out.println("doSome name="+name+"   age="+age);
        ModelAndView mv=new ModelAndView();
        mv.addObject("myname",name);
        mv.addObject("myage",age);
        mv.setViewName("show");
        return mv;
     }

}
