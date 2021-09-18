package ncu.chenyuhan;

import ncu.chenyuhan.service.SomeService;
import ncu.chenyuhan.service.impl.SomeServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    /**
     * spring默认创建对象的时间：在创建spring的容器时，会创建配置文件中的所有的对象
     */
        @Test
        public void test01(){
            SomeService service=new SomeServiceImpl();
            service.doSome();
        }

        @Test
        public void test02(){
           //使用spring容器创建的对象
        //1.指定spring配置文件的名称
        String config="beans.xml";
        //2.创建表示spring容器的对象，ApplicationContext
            //ApplicationContext就是表示Spring容器，通过容器获取对象了
            //ClassPathXmlApplicationContext:表示从类路径中加载spring的配置文件
            ApplicationContext ac=new ClassPathXmlApplicationContext(config);

            //从容器中获取某个对象，要调用对象的方法
            //getBean("要配置文件中的bean的id值")
            SomeService service=(SomeService) ac.getBean("someService");

            //使用spring创建好的对象
            service.doSome();

        }
}
