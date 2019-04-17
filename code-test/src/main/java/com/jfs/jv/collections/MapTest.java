package com.jfs.jv.collections;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {

    public static void main(String[] args) {
        //jdk8 hashMap key hashcode 方法
        String key = "hello";
        int h = key.hashCode();
        System.out.println(h);
        System.out.println(h >>> 16);
        System.out.println(h ^ (h >>> 16));

        HashMap<String ,String> hashMap = new HashMap<>();

        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
    }
}
