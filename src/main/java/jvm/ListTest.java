package jvm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {

        array2List();
    }

    private static void list2Array(){
        List<String> list = new ArrayList<String>(2);
        list.add("guan");
        list.add("bao");
        String[] array = new String[list.size()];
        array = list.toArray(array);
    }

    private static void array2List(){
        String[] array = {"1","2"};
        List<String> list = Arrays.asList(array);
        //list.add("3"); //出错，这里的Arrays.asList返回的只是Arrays的内部类ArrayList并无实现add、remove方法
        array[0] = "3";  //list内容随之改变
        for (String a:list) {
            System.out.printf(a);
        }
    }
}
