package ncu.chenyuhan;

import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import ncu.chenyuhan.dao.StudentDao;
import ncu.chenyuhan.domain.Student;
import ncu.chenyuhan.utils.MyBatisUtils;
import ncu.chenyuhan.vo.QueryParam;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMyBatis {
    @Test
    public void testSelectStudentById(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);

        Student student= dao.selectStudentById(1002);

        System.out.println("student="+student);
    }

    @Test
    public void testSelectMultiParam(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);

        List<Student> students=dao.selectMultiParam("李四",20);
        for (Student stu:students){
            System.out.println("学生="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectMultiObject(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);

        QueryParam param=new QueryParam("张三",28);
        List<Student> students=dao.selectMultiObject(param);
        for (Student stu:students){
            System.out.println("学生="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectMultiPosition(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);

        List<Student> students=dao.selectMultiPosition("李四",20);
        for (Student stu:students){
            System.out.println("学生="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectMultiByMap(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);

        Map<String ,Object> data=new HashMap<>();
        data.put("myname","张三");
        data.put("myage",28);

        List<Student> students=dao.selectMultiByMap(data);
        for (Student stu:students
             ) {
            System.out.println("学生="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectUse$Order(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);

        List<Student> students=dao.selectUse$order("name");
        for (Student stu:students
        ) {
            System.out.println("学生="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectMap(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);

        Map<Object,Object> map=dao.selectMapById(1001);
        System.out.println("map="+map);
    }

    @Test
    public void testSelectAllStudents(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);

        List<Student> students=dao.selectAllStudents();

        for (Student stu:students){
            System.out.println("学生="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectLikeOne(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);

        //准备好like的内容
        String name="%李%";
        List<Student> students=dao.selectLikeOne(name);

        for (Student stu:students){
            System.out.println("学生="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectLikeTwo(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);

        //准备好like的内容
        String name="李";
        List<Student> students=dao.selectLikeOne(name);

        for (Student stu:students){
            System.out.println("学生="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void selectStudentIf(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);

        Student student=new Student();
        student.setName("李四");
        student.setAge(20);
        List<Student> students=dao.selectStudentIf(student);

        for (Student stu:students){
            System.out.println("if==="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void selectStudentWhere(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);

        Student student=new Student();
        student.setName("李四");
        student.setAge(18);
        List<Student> students=dao.selectStudentIf(student);

        for (Student stu:students){
            System.out.println("where==="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectForeachOne(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);

        List<Integer> list=new ArrayList<>();
        list.add(1001);
        list.add(1002);
        list.add(1003);

        List<Student> students=dao.selectForeachOne(list);

        for (Student stu:students){
            System.out.println("foreach--one==="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectForeachTwo(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);

        List<Student> stuList=new ArrayList<>();
        Student s1=new Student();
        s1.setId(1002);
        stuList.add(s1);

        s1=new Student();
        s1.setId(1005);
        stuList.add(s1);

        List<Student> students=dao.selectForeachTwo(stuList);
        for (Student stu:students
             ) {
            System.out.println("foreach--two==="+stu);
        }
    }

    @Test
    public void testSelectAllPageHelper(){
        SqlSession sqlSession=MyBatisUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);

        //加入PageHelper的方法，分页
        //pageNum:第几页，从1开始
        //pageSize:一页中有多少行数据
        PageHelper.startPage(1,3);

        List<Student> students=dao.selectAll();
        for (Student stu:students
        ) {
            System.out.println("foreach--two==="+stu);
        }
    }
}
