package com.jfs.thread;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: caiweiwei
 * @Date: 2019-05-20 16:58
 */
public class CompletableFutureTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        List<String> stringList = new ArrayList<>();
        List<String> testList = Lists.newArrayList();
        testList.add("1");
        testList.add("2");
        long start = System.currentTimeMillis();
        CompletableFuture[] futures = testList.stream().
                map(t -> CompletableFuture
                        .supplyAsync(() ->  {
                            /*if (true) {
                                throw new RuntimeException("Something wrong");
                            }*/
                            return "1";
                        }, executor)
                        .whenComplete((v,e)->{
                            stringList.add(v);
                        })
                        ).toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures);
        //completableFuture.get(3000, TimeUnit.MILLISECONDS);
        System.out.println("block finish, consume time:" + (System.currentTimeMillis() - start));

        executor.shutdown();


    }

    public static void test(){
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1 finished!");
            return "future1 finished!";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2 finished!");
            return "future2 finished!";
        });
        CompletableFuture<Void> combindFuture = CompletableFuture.allOf(future1, future2);
        try {
            combindFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("future1: " + future1.isDone() + " future2: " + future2.isDone());
    }


}
