package ncu.chenyuhan.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class MyInterceptor2 implements HandlerInterceptor {

    private long btime=0;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        btime=System.currentTimeMillis();
        System.out.println("222拦截器的MyInterceptor的preHandle()");

        //给浏览器一个返回结果
        //request.getRequestDispatcher("/tips.jsp").forward(request,response);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("222拦截器的MyInterceptor的postHandle()");
        //对原来的doSome执行结果，需要调整。
        if (modelAndView!=null){
            //修改数据
            modelAndView.addObject("mydate",new Date());
            //修改视图
            modelAndView.setViewName("other");
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        System.out.println("222拦截器的MyInterceptor的afterCompletion()");

        long etime=System.currentTimeMillis();
        System.out.println("计算从preHandle到请求处理完成的时间："+(etime-btime));
    }
}
