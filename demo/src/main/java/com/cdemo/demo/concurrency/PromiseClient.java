package com.cdemo.demo.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @description:
 * @author: star
 * @time: 2021/4/22 13:40
 */
public class PromiseClient {

    public static void main(String[] args) throws Exception {
        PromiseClient client = new PromiseClient();
//        client.test2();
//        client.test3();
        client.test3Same();


    }

    /**
     *  supplyAsync() 执行异步方法
     *  thenApply() 表示执行成功后再串联另外一个异步方法
     *  最后是 thenAccept() 来处理最终结果
     *
     *  Class::function的时候function是属于Class的，应该是静态方法。
     *  this::function的funtion是属于这个对象的。
     */
    private String test1(){

//        final CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(this::find).thenApply(this::go).thenAccept(this::notify);
        return null;
    }

    private void test2() throws Exception {
        CompletableFuture<String> execute = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("execute");
            return "星星";
        }).thenApply(b -> {
            System.out.println("b");
            return "b";
        });

//        String s = execute.get(2, TimeUnit.SECONDS);
//        String s = execute.getNow("默认值");
        String s = execute.get();
        System.out.println("ss:"+ s);
    }

    private void test3() throws Exception {
        CompletableFuture<String> execute = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("a");
                return "a";
            }
        }).thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {
                System.out.println("s:" +s);
                System.out.println("b");
                return s + "b";
            }
        });

        String s = execute.get();
        System.out.println("result:"+ s);
    }

    private void test3Same() throws Exception {
        CompletableFuture<String> execute = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "a");
            return "a";
        }).thenApply(s -> {
            System.out.println(Thread.currentThread().getName() + "s:" +s);
            System.out.println("b");
            return s + "b";
        });

        String s = execute.get();
        System.out.println(Thread.currentThread().getName() + "result:"+ s);
    }

    private void testCombine() throws Exception {
        CompletableFuture<String> execute1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "a");
            return "a";
        }).thenApply(s -> {
            System.out.println(Thread.currentThread().getName() + "s:" +s);
            System.out.println("b");
            return s + "b";
        });

        CompletableFuture<String> execute2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "a");
            return "a";
        }).thenApply(s -> {
            System.out.println(Thread.currentThread().getName() + "s:" +s);
            System.out.println("b");
            return s + "b";
        });

//        String s = execute1.thenCombine(execute2, (t, u) -> {
//            return t + "" +u;
//        });
        System.out.println(Thread.currentThread().getName() + "result:");
    }


}
