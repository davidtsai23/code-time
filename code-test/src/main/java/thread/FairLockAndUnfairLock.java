package thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class FairLockAndUnfairLock {
    private static ReentrantLockSample fairLock = new ReentrantLockSample(true);
    private static ReentrantLockSample unfairLock = new ReentrantLockSample(false);

    public static void main(String[] args) {
        FairLockAndUnfairLock fairLockAndUnfairLock = new FairLockAndUnfairLock();
        fairLockAndUnfairLock.fairTest();
        //fairLockAndUnfairLock.unfairTest();
    }

    public void fairTest(){
        testLock(fairLock);
    }
    public void unfairTest(){
        testLock(unfairLock);
    }

    private void testLock(ReentrantLockSample lock){
        for (int i=0;i<5;i++){
            JobThread jobThread = new JobThread(lock);
            Thread thread = new Thread(jobThread);
            thread.setName("Thread"+i);
            thread.start();
        }
    }

    private static class JobThread implements Runnable{

        private ReentrantLockSample lock;

        public JobThread(ReentrantLockSample lock) {
            this.lock = lock;
        }

        public void run() {
            //while (true){
                lock.lock();
                Collection<Thread> collections = lock.getQueuedThread();

                System.out.println(Thread.currentThread().getName() + " wait queue:"+collections);
                System.out.println(Thread.currentThread().getName() + " wait queue:"+collections);
                lock.unlock();
            //}

        }
    }

    private static class ReentrantLockSample extends ReentrantLock{
        public ReentrantLockSample(boolean fair) {
            super(fair);
        }
        public Collection<Thread> getQueuedThread(){
            List<Thread> threadList = new ArrayList<Thread>(super.getQueuedThreads());
            return threadList;
        }
    }
}
