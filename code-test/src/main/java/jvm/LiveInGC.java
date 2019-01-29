package jvm;

public class LiveInGC {
    private static LiveInGC liveInGC = null;

    private void isAlive(){
        System.out.println("我还活着");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        liveInGC = this;
    }

    public static void main(String[] args) throws InterruptedException {
        liveInGC = new LiveInGC();
        saveMyself();//第一次自救
        saveMyself();//第二次自救
    }

    private static void saveMyself() throws InterruptedException {
        //置空等待垃圾回收
        liveInGC = null;
        System.gc();
        //finalize方法优先级比较低等待一下让liveInGC自救
        Thread.sleep(1000);
        if (liveInGC == null){
            System.out.println("我被回收了");
        }else {
            liveInGC.isAlive();
        }
    }
}
