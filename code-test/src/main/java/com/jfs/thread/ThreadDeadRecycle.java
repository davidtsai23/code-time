package com.jfs.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 线程死循环
 */
public class ThreadDeadRecycle {

    public static void createBusyThread(){
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true){
                    //System.out.println("thread1");
                }
            }
        },"BusyThread");
        thread.start();
    }


    public static void createWaitThread(final Object object){
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                synchronized (object){
                    try {
                        object.wait(1000*30);//等待30S后唤醒
                        object.notify();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        },"WaitThread");
        thread.start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        createBusyThread();
        bufferedReader.readLine();
        Object object = new Object();
        createWaitThread(object);
    }
}
