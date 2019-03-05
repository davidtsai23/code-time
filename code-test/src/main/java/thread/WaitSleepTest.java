package thread;

public class WaitSleepTest {


    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        new Thread(new WaitThread(object)).start();
        Thread.sleep(1000);//保证两个线程的执行顺序，休息1S再启动下边线程
        new Thread(new SleepThread(object)).start();
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
                    object.wait();//Object 方法
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
                    Thread.sleep(2000);
                    object.notify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread:"+Thread.currentThread().getName()+"exit,Time:"+System.currentTimeMillis());
            }
        }
    }


}




