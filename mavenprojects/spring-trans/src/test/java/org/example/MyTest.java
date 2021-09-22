package org.example;

import org.example.service.BuyGoodsService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test01(){
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);

        //从容器中获取service
        BuyGoodsService service=(BuyGoodsService) ctx.getBean("buyService");

        //jdk.proxy2.$Proxy16  :jdk动态代理对象
        System.out.println("service是代理："+service.getClass().getName());
        //调用方法
        service.buy(1002,10);
    }
}
