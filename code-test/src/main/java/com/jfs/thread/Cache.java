package com.jfs.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache {
    static Map<String,Object> map = new HashMap<>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();

    public static final Object get(String key){
        r.lock();
        try {
            return map.get(key);
        }finally {
            r.unlock();
        }
    }

    public static final Object put(String key,Object value){
        w.lock();
        try {
           return map.put(key,value);
        }finally {
            w.unlock();
        }
    }

    public static void clear(){
        w.lock();
        try {
            map.clear();
        }finally {
            w.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("准备获取数据");
                    latch.await();
                    for (int i = 0;i<100;i++){
                        System.out.println("获取："+get(i+""));
                    }
//                    latch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("准备放入数据");
                    latch.await();
                    for (int i = 0;i<100;i++){
                        put(i+"",i);
                    }
//                    latch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();



        latch.countDown();

    }
}
