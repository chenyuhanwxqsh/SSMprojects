package ncu.chenyuhan.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @RequestMapping(value ="/some.do")
    public ModelAndView doSome(String name,Integer age){
        System.out.println("==执行MyController中的doSome方法===");
        ModelAndView mv=new ModelAndView();

        mv.addObject("myname",name);
        mv.addObject("myage",age);

        mv.setViewName("show");
        return mv;
    }

}
