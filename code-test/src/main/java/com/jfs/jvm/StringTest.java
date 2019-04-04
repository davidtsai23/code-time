package com.jfs.jvm;

import junit.framework.TestCase;

public class StringTest extends TestCase {


    public static void internTest(){
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        //intern 这个方法是一个 native 的方法，但注释写的非常明了。
        // “如果常量池中存在当前字符串, 就会直接返回当前字符串. 如果常量池中没有此字符串, 会将此字符串放入常量池中后, 再返回”。
        System.out.println(s3.intern());
        String s4 = "11";
        System.out.println(s3 == s4);

    }

    public void testhuiwen(){
        String a = "12345 4321";
        String str = a.replaceAll("\\s","");
        System.out.println(str);
    }
}
