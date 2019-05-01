package com.jfs.thread;

/**
 * @author tsaidavid
 * @date 2019/5/1
 * @description
 **/
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
           while (true){
               try {
                   Thread.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
        //设置为守护线程，如果注释掉则程序不会退出结束，即使Main线程执行完毕
        //thread.setDaemon(true);
        thread.start();
        System.out.println("Main thread finished");
    }
}
