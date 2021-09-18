package ncu.chenyuhan.dao;

import ncu.chenyuhan.domain.Student;
import ncu.chenyuhan.vo.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    /**
     *一个简单类型的参数
     * 简单类型：mybatis把java的基本数据类型和String都叫简单类型
     *
     * 在mapper文件获取简单类型的一个参数的值，使用#{任意字符}
     */
    public Student selectStudentById(Integer id);

    /**
     * 多个参数：命名参数，在形参定义的前面加入 @Param("自定义参数名称")
     */
    List<Student> selectMultiParam(@Param("myname") String name,
                                  @Param("myage") Integer age);

    /**
     * 多个参数，使用java对象作为接口中方法的参数
     * 这里的java对象用什么都可以很灵活，eg:Student
     */
    List<Student> selectMultiObject(QueryParam param);

    /**
     * 多个参数-简单类型，按位置传值
     * mybatis 3.4之前，使用#{0},#{1}
     * mybatis 3.4之后，使用#{arg0},#{arg1}
     */
    List<Student> selectMultiPosition(String name,Integer age);

    /**
     * 多个参数，使用Map存放多个值
     */
    List<Student> selectMultiByMap(Map<String,Object> map);

    List<Student> selectUse$(@Param("myname") String name);

    List<Student> selectUse$order(@Param("colName") String name);

    //定义方法返回Map
    Map<Object,Object> selectMapById(Integer id);

    /**
     * 使用resultMap定义映射关系
     */
    List<Student> selectAllStudents();

    //第一种模糊查询，在java代码指定like的内容
    List<Student> selectLikeOne(String name);

    //name就是李这个值，在mapper中拼接like "%" 李 "%"
    List<Student> selectLikeTwo(String name);

    //动态sql，使用java对象作为参数
    List<Student> selectStudentIf(Student student);

    //where使用
    List<Student> selectStudentWhere(Student student);

    //foreach用法 1
    List<Student> selectForeachOne(List<Integer> idlist);

    //foreach用法2
    List<Student> selectForeachTwo(List<Student> stulist);

    //使用PageHelper分页数据
    List<Student> selectAll();
}
