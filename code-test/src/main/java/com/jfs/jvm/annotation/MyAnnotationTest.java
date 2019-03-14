package com.jfs.jvm.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Copyright("jd")
public class MyAnnotationTest {

    @MyAnnotation
    public void doSomething()
    {
        System.out.println("hello world");
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method[] methods  = MyAnnotationTest.class.getDeclaredMethods();
        for (Method method:methods){
            if(method.isAnnotationPresent(MyAnnotation.class)){
                System.out.println(method.getAnnotation(MyAnnotation.class));
            }

            if (method.isAnnotationPresent(RequestForEnhancement.class)){
                System.out.println(method.getAnnotation(RequestForEnhancement.class).toString());
            }
        }

        Annotation[] annotations = MyAnnotationTest.class.getAnnotations();
        for (Annotation annotation:annotations){
            System.out.println(annotation.toString());
        }

    }

    @RequestForEnhancement(
            id       = 2868724,
            synopsis = "Enable time-travel",
            engineer = "Mr. Peabody",
            date     = "4/1/3007"
    )
    public void travelThroughTime(Date destination) {

    }
}
