package thread;

public class WaitSleepTest {


    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        Thread thread1 = new Thread(new WaitThread(object));
        thread1.start();
        Thread.sleep(1000);//保证两个线程的执行顺序，休息1S再启动下边线程
        Thread thread2 = new Thread(new SleepThread(object));
        thread2.start();
    }

    private static class WaitThread implements Runnable{
        Object object;
        public WaitThread(Object o) {
            object = o;
        }

        @Override
        public void run() {
            synchronized (object){
                System.out.println("thread:"+Thread.currentThread().getName()+"enter,Time:"+System.currentTimeMillis());
                try {
                    object.wait();//Object 方法，线程释放当前对象锁进入等待对象锁定池进行等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread:"+Thread.currentThread().getName()+"exit,Time:"+System.currentTimeMillis());
            }
        }
    }

    private static class SleepThread implements Runnable{
        Object object;
        public SleepThread(Object o) {
            object = o;
        }

        @Override
        public void run() {
            synchronized (object){
                System.out.println("thread:"+Thread.currentThread().getName()+"enter,Time:"+System.currentTimeMillis());

                try {
                    //object.wait(2000);
                    Thread.sleep(2000); //不会释放当前对象锁，直到走完2S执行完synchronized代码块
                    //object.notify(); //如果注释掉这一句，那么Thread-0exit将不会输出，针对wait()方法，而wait(5000)没有notify也会继续执行
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread:"+Thread.currentThread().getName()+"exit,Time:"+System.currentTimeMillis());
            }
        }
    }


}




