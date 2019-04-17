package com.jfs.jv.repeatabletest;


import java.lang.annotation.Repeatable;

/**
 * @Repeatable括号内的就相当于用来保存该注解内容的容器
 */
@Repeatable(Persons.class)
public @interface Person {
    String role() default "";
}
