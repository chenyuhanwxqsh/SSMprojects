package ncu.chenyuhan.dao;

import ncu.chenyuhan.domain.Student;

import java.util.List;

public interface StudentDao {
    List<Student> selectStudents();

    int insertStudents(Student student);
}
