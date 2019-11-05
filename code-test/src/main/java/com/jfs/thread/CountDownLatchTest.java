package com.jfs.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i=0;i < countDownLatch.getCount(); i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("子线程"+Thread.currentThread().getName()+"准备执行"+System.currentTimeMillis());
                    try {
                        Thread.sleep(1000);
                        System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕"+System.currentTimeMillis());
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }

        try {
            Thread.sleep(1000);
            System.out.println("等待countDownLatch减为0即所有子线程执行完成自己的操作");
            countDownLatch.await();
            System.out.println("所有子线程执行完毕，继续执行main主线程");
            System.out.println("老哥走起");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
