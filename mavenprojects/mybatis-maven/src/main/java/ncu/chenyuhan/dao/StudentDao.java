package ncu.chenyuhan.dao;

import ncu.chenyuhan.domain.Student;

import java.util.List;

//接口操作student表
public interface StudentDao {

    //查询student表的所有数据
    public List<Student> selectStudents();
    //一个方法是对应一个SQL的执行

    //插入方法
    //参数：student,表示要插入到数据库的数据
    //返回值：int,表示执行insert操作后的影响数据库的行数
    public int insertStudent(Student student);
}
