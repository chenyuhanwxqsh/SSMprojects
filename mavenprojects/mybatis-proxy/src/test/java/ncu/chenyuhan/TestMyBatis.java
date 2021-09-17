package ncu.chenyuhan;

import ncu.chenyuhan.dao.StudentDao;
import ncu.chenyuhan.domain.Student;
import ncu.chenyuhan.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestMyBatis {
    @Test
    public void testSelectStudents(){
        /*
        //ncu.chenyuhan.dao.StudentDao
        StudentDao dao =new StudentDaoImpl();
        List<Student> studentList=dao.selectStudents();*/
        /**
         * List<Student> studentList=dao.selectStudents();调用
         * 1.dao对象，类型是StudentDao,全限定名称是:ncu.chenyuhan.dao.StudentDao
         *   全限定名称和namespace是一样的
         * 2.方法名称,selectStudents,这个方法就是mapper文件中的id值selectStudents
         * 3.通过dao中方法的返回值也可以确定MyBatis要调用的SqlSession的方法
         *   如果返回值是List,调用的是SqlSession.selectList()方法.......
         *
         *   mybatis的动态代理：mybatis根据dao的方法调用，获取执行sql语句的信息
         *      mybatis根据你的dao接口，创建出一个dao接口的实现类，并创建这个类的对象
         *      完成SqlSession调用方法，访问数据库
         */
        /*
        for (Student stu:studentList){
            System.out.println(stu);
        }*/


        /**
         * 使用mybatis的动态代理机制，使用SqlSession.getMapper(dao接口.class)
         * getMapper能获取dao接口对于的实现类对象
         */
        SqlSession sqlSession= MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);

        //com.sun.proxy.$proxy2:jdk的动态代理(dao=jdk.proxy2.$Proxy4)
        System.out.println("dao="+dao.getClass().getName());
        //调用dao的方法，执行数据库的操作
        List<Student> students=dao.selectStudents();
        for (Student stu:students
             ) {
            System.out.println("学生="+stu);
        }
    }

    @Test
    public void testInsertStudent(){
        /*StudentDao dao=new StudentDaoImpl();
        Student student=new Student(1005,"顿山","dunshan@qq.com",55);
        int nums=dao.insertStudent(student);
        sqlSession.commit();
        System.out.println("添加对象的数量:"+nums);*/

        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);
        Student student=new Student(1006,"xzq","xzq@qq.com",3);
        int nums=dao.insertStudents(student);
        sqlSession.commit();
        System.out.println("添加对象的数量:"+nums);

    }
}
