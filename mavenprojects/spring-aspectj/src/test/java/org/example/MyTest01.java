package org.example;

import org.example.ba01.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest01 {
    @Test
    public void test01(){
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);

        //从容器中获取目标对象
        SomeService proxy=(SomeService) ctx.getBean("someService");
        //proxy:jdk.proxy2.$Proxy10   jdk的动态代理
        System.out.println("proxy:"+proxy.getClass().getName());
        //通过代理的对象执行方法，实现目标方法执行时，增强了功能
        proxy.doSome("lisi",20);

        String res=proxy.doOther("zs",28);
        System.out.println("====="+res);

        String str=proxy.doFirst("ch",23);//myround()
        System.out.println("str==="+str);

        proxy.doSecond();

        proxy.doThird();
    }
}
