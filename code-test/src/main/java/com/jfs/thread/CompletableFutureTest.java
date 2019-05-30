package com.jfs.thread;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: caiweiwei
 * @Date: 2019-05-20 16:58
 */
public class CompletableFutureTest extends TestCase {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(20);
//        thenApply();
//        thenCombine();
//        exception();
//        whenComplete();
//        handle();
//        forTest();
    }

    private static String mockRpc(String s) {
        try {
            if ("1".equals(s)){

                Thread.sleep(1000);
            }
            if ("2".equals(s)){
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void thenApply(){
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> "任务A");
        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> "任务B");
        CompletableFuture<String> futureC = futureB.thenApply(b -> {
            System.out.println("执行任务C.");
            System.out.println("参数:" + b);//参数:任务B
            return "a";
        });
    }

    /**
     * 需要根据商品id查询商品的当前价格,分两步,查询商品的原始价格和折扣,这两个查询相互独立,当都查出来的时候用原始价格乘折扣,算出当前价格.
     */
    public static void thenCombine(){
        CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(() -> 100d);
        CompletableFuture<Double> futureDiscount = CompletableFuture.supplyAsync(() -> 0.8);
        CompletableFuture<Double> futureResult = futurePrice.thenCombine(futureDiscount, (price, discount) -> price * discount);
        System.out.println("最终价格为:" + futureResult.join()); //最终价格为:80.0
    }


    public static void exception(){
        CompletableFuture<String> futureA = CompletableFuture.
                supplyAsync(() -> "执行结果:" + (100 / 0))
                .thenApply(s -> "futureA result:" + s)
                .exceptionally(e -> {
                    System.out.println(e.getMessage()); //java.lang.ArithmeticException: / by zero
                    return "futureA result: 100";
                });
        CompletableFuture<String> futureB = CompletableFuture.
                supplyAsync(() -> "执行结果:" + 50)
                .thenApply(s -> "futureB result:" + s)
                .exceptionally(e -> "futureB result: 100");
        System.out.println(futureA.join());//futureA result: 100
        System.out.println(futureB.join());//futureB result:执行结果:50
    }


    public static void whenComplete(){
        CompletableFuture<String> futureA = CompletableFuture.
                supplyAsync(() -> "执行结果:" + (100 / 0))
                .thenApply(s -> "apply result:" + s)
                .whenComplete((s, e) -> {
                    if (s != null) {
                        System.out.println(s);//未执行
                    }
                    if (e == null) {
                        System.out.println(s);//未执行
                    } else {
                        System.out.println(e.getMessage());//java.lang.ArithmeticException: / by zero
                    }
                })
                .exceptionally(e -> {
                    System.out.println("ex"+e.getMessage()); //ex:java.lang.ArithmeticException: / by zero
                    return "futureA result: 100"; });
        System.out.println(futureA.join());//futureA result: 100


        CompletableFuture<String> futureB = CompletableFuture.
                supplyAsync(() -> "执行结果:" + (100 / 0))
                .thenApply(s -> "apply result:" + s)
                .exceptionally(e -> {
                    System.out.println("ex:"+e.getMessage()); //ex:java.lang.ArithmeticException: / by zero
                    return "futureB result: 100";
                })
                .whenComplete((s, e) -> {
                    if (e == null) {
                        System.out.println(s);//futureA result: 100
                    } else {
                        System.out.println(e.getMessage());//未执行
                    }
                })
                ;
        System.out.println(futureB.join());//futureA result: 100
    }

    public static void handle(){
        CompletableFuture<String> futureA = CompletableFuture.
                supplyAsync(() -> "执行结果:" + (100 / 0))
                .thenApply(s -> "apply result:" + s)
                .exceptionally(e -> {
                    System.out.println("ex:" + e.getMessage()); //java.lang.ArithmeticException: / by zero
                    return "futureA result: 100";
                })
                .handle((s, e) -> {
                    if (e == null) {
                        System.out.println(s);//futureA result: 100
                    } else {
                        System.out.println(e.getMessage());//未执行
                    }
                    return "handle result:" + (s == null ? "500" : s);
                });
        System.out.println(futureA.join());//handle result:futureA result: 100
    }

    public void testAllOf(){

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<String> list = Arrays.asList("1","2","3","4");
        long start = System.currentTimeMillis();
        CompletableFuture[] futures = list.stream().map(s ->
                CompletableFuture.supplyAsync(() -> callRpc(s),executorService)
                .applyToEither(timeoutAfter(1000,TimeUnit.MILLISECONDS),a -> "超时")
                .handle((a,e)->{
                    if ("超时".equals(a)){
                        return s+"超时";
                    }
                    return s;
                }).whenComplete((a,e)->{
                    System.out.println(a);
                })).toArray(CompletableFuture[]::new);


        CompletableFuture.allOf(futures).join();

        System.out.println("allof总耗时:" + (System.currentTimeMillis() - start));

        executorService.shutdown();
    }


    public  void testForFuture(){
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        long start = System.currentTimeMillis();
        List<Future<String>> futureList = new ArrayList<>();
        List<String> list = Arrays.asList("1","2","3","4");
        for (String a:list) {
            Callable<String> task = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return callRpc(a);
                }
            };

            Future<String> future = executorService.submit(task);
            futureList.add(future);
        }

        for (int i = 0;i<futureList.size();i++){
            Future<String> task = futureList.get(i);
            try {
                String result = task.get(1000,TimeUnit.MILLISECONDS);
                System.out.println(result);
            } catch (TimeoutException e) {
                System.out.println(list.get(i)+"超时");
            } catch (Exception e){
                System.out.println(list.get(i)+"异常");
            }

        }

        System.out.println("forTest总耗时:" + (System.currentTimeMillis() - start));

        executorService.shutdown();
    }

    public static String callRpc(String a){

        try {
            Thread.sleep(1000*Integer.parseInt(a));
            System.out.println("task线程：" + Thread.currentThread().getName()
                    + "任务a=" + a + ",完成！+" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return a;
    }

    public static <T> CompletableFuture<T> timeoutAfter(long timeout, TimeUnit unit) {
        CompletableFuture<T> result = new CompletableFuture<T>();
        Delayer.delay(() -> result.completeExceptionally(new TimeoutException()), timeout, unit);
        return result;
    }

    static final class Delayer {
        static ScheduledFuture<?> delay(Runnable command, long delay,
                                        TimeUnit unit) {
            return delayer.schedule(command, delay, unit);
        }

        static final class DaemonThreadFactory implements ThreadFactory {
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setName("CompletableFutureDelayScheduler");
                return t;
            }
        }

        static final ScheduledThreadPoolExecutor delayer;
        static {
            (delayer = new ScheduledThreadPoolExecutor(
                    1, new DaemonThreadFactory())).
                    setRemoveOnCancelPolicy(true);
        }
    }


    public void test1(){
        long start = System.currentTimeMillis();
        // 结果集
        List<String> list = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Integer> taskList = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
        // 全流式处理转换成CompletableFuture[]+组装成一个无返回值CompletableFuture，join等待执行完毕。返回结果whenComplete获取
        CompletableFuture[] cfs = taskList.stream()
                .map(t -> CompletableFuture.supplyAsync(() -> calc(t), executorService)
                        .applyToEither(timeoutAfter(800,TimeUnit.MILLISECONDS),a -> "超时")
                        .whenComplete((s, e) -> {
                            System.out.println("任务"+s+"完成!result="+s+"，异常 e="+e+","+new Date());
                            list.add(s);
                        })
                ).toArray(CompletableFuture[]::new);
        // 封装后无返回值，必须自己whenComplete()获取
        CompletableFuture.allOf(cfs).join();
        System.out.println("list="+list+",耗时="+(System.currentTimeMillis()-start));
    }

    public int calc(Integer i) {
        try {
            if (i == 1) {
                Thread.sleep(3000);//任务1耗时3秒
            } else if (i == 5) {
                Thread.sleep(5000);//任务5耗时5秒
            } else {
                Thread.sleep(1000);//其它任务耗时1秒
            }
            System.out.println("task线程：" + Thread.currentThread().getName()
                    + "任务i=" + i + ",完成！+" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }
}
