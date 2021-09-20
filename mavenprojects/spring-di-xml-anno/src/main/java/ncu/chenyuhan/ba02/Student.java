package ncu.chenyuhan.ba02;

public class Student {
    //声明一个引用类型
    private School school;
    private  String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "school=" + school +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
