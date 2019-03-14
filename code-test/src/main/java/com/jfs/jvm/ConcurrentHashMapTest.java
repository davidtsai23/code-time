package com.jfs.jvm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    private ConcurrentHashMap<Integer,Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
         System.out.println("Before allocate map, free memory is " + Runtime.getRuntime().freeMemory()/(1024*1024) + "M");
         Map<String, String> map = new ConcurrentHashMap<String, String>(1073741825);
         System.out.println("After allocate map, free memory is " + Runtime.getRuntime().freeMemory()/(1024*1024) + "M");
         int i = 0;
         try {
                 while (i < 10) {
                         System.out.println("Before put the " + (i + 1) + " element, free memory is " + Runtime.getRuntime().freeMemory()/(1024*1024) + "M");
                     /**
                      * put 操作判断存储node大小，如果node不够存储以上初始化大小，则new Node初始化分配存储空间
                      * 此时，分配的大小为默认最大值2^30=1,073,741,824（因为给的初始化值1073741825大于默认最大值），
                      * 这些初始化的node空对象单个大小为8byte，参考http://www.importnew.com/18878.html，
                      * 那么2^30 * 8 = 2^34 bytes，大小为 2^34 /(2^10 *2^10) = 2^14 M = 4G内存空间，
                      * 显然初始分配的512M是远远不够的
                      */
                     map.put(String.valueOf(i), String.valueOf(i));
                         System.out.println("After put the " + (i + 1) + " element, free memory is " + Runtime.getRuntime().freeMemory()/(1024*1024) + "M");
                         i++;
                     }
             } catch (Exception e) {
                 e.printStackTrace();
             } catch (Throwable t) {
                 t.printStackTrace();
             } finally {
                 System.out.println("map size is " + map.size());
             }
    }
}
