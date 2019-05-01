package com.jfs.thread;

/**
 * 死锁
 */
public class DeadLock {

    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new DeadLock().testDeadLock();
    }

    private void testDeadLock(){
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("thread1");
                synchronized (A){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("com.jfs.thread lock B");
                    }
                }

            }
        });

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("thread2");
                synchronized (B){
                    synchronized (A){
                        System.out.println("com.jfs.thread lock A");
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
