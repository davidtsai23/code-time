package com.jfs.jvm.repeatabletest;

import java.lang.annotation.Annotation;

public class RepeatableTest {

    public static void main(String[] args) {
        Annotation[] annotations = Man.class.getAnnotations();
        System.out.println(annotations.length);
        Persons persons1 = (Persons) annotations[0];
        for (Person p:persons1.value()){
            System.out.println(p.role());
        }
    }
}
