package com.jfs.jv.jvm;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * java.util.function.Predicate是在java 8中引入的functional interface
 * Predicate用于分配lambda表达式
 * functional interface是返回布尔值的test（T t）
 * 当我们将对象传递给这个方法时，它将通过分配的lambda表达式来评估对象
 */
public class PredicateTest {
    public static void main(String[] args) {
        Predicate<String> predicate = (v1) -> {
            return v1.equals("1");
        };
        System.out.println(predicate.test("1"));

        Predicate<Student> maleStudent = s-> s.age >= 20 && "male".equals(s.gender);
        Predicate<Student> femaleStudent = s-> s.age > 15 && "female".equals(s.gender);
        Predicate<Student> boy = student -> student.gender.equals("boy");
        Predicate<Student> girl = student -> student.gender.equals("girl");

        Function<Student,String> maleStyle = s-> "Hi, You are male and age "+s.age;
        Function<Student,String> femaleStyle = s-> "Hi, You are female and age "+ s.age;

        Student s1 = new Student(21,"girl");
        if(boy.test(s1)){
            System.out.println(s1.customShow(maleStyle));
        }else if(girl.test(s1)){
            System.out.println(s1.customShow(femaleStyle));
        }
    }

}

class Student {
    public int age;
    public String gender;

    public Student(int age, String gender) {
        this.age = age;
        this.gender = gender;
    }

    public String customShow(Function<Student, String> fun) {
        return fun.apply(this);
    }
}