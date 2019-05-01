package thread;

public class WaitSleepTest extends Thread{

    CommonResource commonResource = new CommonResource();

    public static void main(String[] args) {


        WaitSleepTest sleepThread = new WaitSleepTest();
        WaitSleepTest sleepThread2 = new WaitSleepTest();

        sleepThread.start();
        sleepThread2.start();

    }



    @Override
    public void run() {
        System.out.println("current thread is:"+Thread.currentThread().getName());

    }
}

class CommonResource{


}