package ncu.chenyuhan;

import ncu.chenyuhan.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMyBatis {
    //测试方法，测试功能
    @Test
    public void testInsert() throws IOException {
        //访问mybatis读取student数据
        //1.定义mybatis主配置文件的名称，从类路径的根开始(target/classes)
        String config="mybatis.xml";
        //2.读取这个config表示的文件
        InputStream in = Resources.getResourceAsStream(config);
        //3.创建了SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        //4.创建SqlSessionFactory对象
        SqlSessionFactory factory=builder.build(in);
        //5.【重要】获取SqlSession对象，从SqlSessionFactory中获取SqlSession
        SqlSession sqlSession=factory.openSession();
        //6【重要】指定要执行的sql语句的标识。sql映射文件中的namespace+"."+标签的id值
        //String sqlId="ncu.chenyuhan.dao.StudentDao"+"."+"selectStudents";
        String sqlId="ncu.chenyuhan.dao.StudentDao.insertStudents";
        //7.执行sql语句，通过sqlId找到语句
        Student student=new Student(1003,"张飞","zhangfei@163.com",20);
        int nums=sqlSession.insert(sqlId,student);

        //mybatis默认不是自动提交事务的，所以在insert，update,delete后手动提交事务
        sqlSession.commit();
        //8.输出结果
        System.out.println("执行insert的结果："+nums);
        //9.关闭SqlSession对象
        sqlSession.close();
    }
}
