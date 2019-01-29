package jvm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {

        System.out.println(4>>1);
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

    @Test
    public void forRemove(){
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        /*for (String temp : a) {
            if ("2".equals(temp)) {
                a.remove(temp);
            }
        }*/

        for (int i=0;i<a.size();i++){
            if ("2".equals(a.get(i))) {
                a.remove(a.get(i));
            }
        }

        for (String temp : a) {
            System.out.println(temp);
        }
    }
}
