package ncu.chenyuhan.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class MyInterceptor implements HandlerInterceptor {

    //验证登录的用户信息，正确return true,其它return false
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("");
        String loginName="";
        //从session中获取name的值
        Object attr=request.getSession().getAttribute("name");
        if (attr!=null){
            loginName=(String) attr;
        }

        //判断登录的账户，是否符合要求
        if (!"zs".equals(loginName)){
            //不能访问系统
            //给用户提示
            request.getRequestDispatcher("/tips.jsp").forward(request,response);
            return false;
        }
        return true;
    }
}
