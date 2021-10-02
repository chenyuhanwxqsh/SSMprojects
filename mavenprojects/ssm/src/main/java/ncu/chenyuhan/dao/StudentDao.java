package ncu.chenyuhan.dao;

import ncu.chenyuhan.domain.Student;

import java.util.List;

public interface StudentDao {
    int insertStudent(Student student);

    List<Student> selectStudents();
}
