package com.sunlong.cloud.eurekaserver;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : shipp
 * @description :
 * @data : 2018/11/14 11:49
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception{
        test();
    }

    public static void test3() {

//        Stream.of(1,2,3,5,6,7).forEach(System.out::println);

        Random random = new Random();
//        Stream.generate(() -> random.nextInt(10)).limit(10).forEach(System.out::println);
        List<Integer> list = Stream.generate(() -> random.nextInt(10)).limit(10).collect(Collectors.toList());
        System.out.println(list);
        Integer[] arr = list.toArray(new Integer[0]);
        System.out.println();
    }

    public static void test2() throws ExecutionException, InterruptedException {
        AgentDataAcceptor tt = new AgentDataAcceptor(20);
        ExecutorService service = Executors.newFixedThreadPool(10);
        Random rand = new Random();

        List<CompletableFuture<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                // 这个地方调用供应商，得到结果之后，return结果
                try {
                    Thread.sleep(1000 + rand.nextInt(20000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int rr = rand.nextInt(100);
                return rr;
            }, service).whenComplete((s, e) -> {
                // 结束之后根据成功与否，把结果通知接收器
                if (e == null){
                    tt.success(s);
                    return;
                }
                tt.failed();
            });
            list.add(future);
        }

        CompletableFuture<Object> f = CompletableFuture.anyOf(list.stream().toArray(CompletableFuture[]::new));
//        CompletableFuture<Object> f = CompletableFuture.anyOf(list.toArray(new CompletableFuture[list.size()]));
        System.out.println(f.get());

        for (int i = 0; i < 10; i++) {
            Thread.sleep(5000);
            System.out.println(tt.getPercentage() + "," + tt.getAcceptCnt().get());
        }
    }

    public static void test1() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        }), s -> s).join();
        System.out.println(result);
    }

    public static void test() {
        Long start = System.currentTimeMillis();
        // 结果集
        List<String> list = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Integer> taskList = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
        // 全流式处理转换成CompletableFuture[]+组装成一个无返回值CompletableFuture，join等待执行完毕。返回结果whenComplete获取
        List<CompletableFuture<Integer>> cfs = taskList.stream()
                .map(integer -> CompletableFuture.supplyAsync(() -> calc(integer), executorService)
//                        .thenApply(h->Integer.toString(h))
                        .whenComplete((s, e) -> {
                            System.out.println("任务"+s+"完成!" + System.currentTimeMillis());
//                            list.add(s);
                        })
                ).collect(Collectors.toList());
        // 封装后无返回值，必须自己whenComplete()获取
//        CompletableFuture<Void> ff = CompletableFuture.allOf(cfs.stream().toArray(CompletableFuture[]::new));
////        List<Integer> ccList =  cfs.stream().map(CompletableFuture::join).collect(Collectors.toList());
////        System.out.println(ccList);
//
//
//
//        CompletableFuture<List<Integer>> aa = ff.thenApply(v -> {
//            System.out.println(v);
//            return cfs.stream().map(CompletableFuture::join).collect(Collectors.toList());
//        });
////
//        try {
//            System.out.println(aa.get());
//            System.out.println(aa.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        System.out.println(System.currentTimeMillis());
        System.out.println(TimeUnit.MICROSECONDS.toNanos(50000000L));
        LockSupport.parkNanos(Thread.currentThread(),TimeUnit.MICROSECONDS.toNanos(500000L));
        System.out.println(System.currentTimeMillis());
//        System.out.println("list="+list+",耗时="+(System.currentTimeMillis()-start));

//        CompletableFuture f1 = new CompletableFuture();
//        CompletableFuture.anyOf();
    }

    public static Integer calc(Integer i) {
        try {
            if (i == 1) {
                Thread.sleep(3000);//任务1耗时3秒
            } else if (i == 5) {
                Thread.sleep(5000);//任务5耗时5秒
            } else {
                Thread.sleep(1000);//其它任务耗时1秒
            }
//            System.out.println("task线程：" + Thread.currentThread().getName()
//                    + "任务i=" + i + ",完成！+" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }
}

