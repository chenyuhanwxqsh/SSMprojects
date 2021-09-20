package org.example;

import org.example.dao.StudentDao;
import org.example.domain.Student;
import org.example.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    @Test
    public void test01(){
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);

        String names[] =ctx.getBeanDefinitionNames();
        for (String na:names
             ) {
            System.out.println("容器中对象名称："+na+"|"+ctx.getBean(na));
        }
    }

    @Test
    public void testDaoInsert(){
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);

        //获取spring容器中的dao对象
        StudentDao dao=(StudentDao) ctx.getBean("studentDao");
        Student student=new Student(1008,"ccc","ccc@qq.com",29);
        int nums=dao.insertStudent(student);
        System.out.println("nums="+nums);
    }

    @Test
    public void testServiceInsert(){
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);

        StudentService studentService=(StudentService) ctx.getBean("studentService");
        Student student=new Student(1011,"cs","cs@qq.com",29);
        int nums=studentService.addStudent(student);
        //spring和mybatis整合在一起使用，事务是自动提交的，无需执行SqlSession.commit();
        System.out.println("nums="+nums);
    }

    @Test
    public void testServiceSelect(){
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);

        StudentService studentService=(StudentService) ctx.getBean("studentService");

        List<Student> students=studentService.queryStudents();
        for (Student s:students
             ) {
            System.out.println(s);
        }
    }
}
