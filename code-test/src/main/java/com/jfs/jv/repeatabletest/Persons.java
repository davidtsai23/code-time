package com.jfs.jv.repeatabletest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 声明一个Persons类用来包含所有的身份
 * @Target是声明Persons注解的作用范围，参数ElementType.Type代表可以给一个类型进行注解，比如类，接口，枚举。
 *
 * @Retention是注解的有效时间，RetentionPolicy.RUNTIME是指程序运行的时候。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Persons {
    Person[] value();
}

