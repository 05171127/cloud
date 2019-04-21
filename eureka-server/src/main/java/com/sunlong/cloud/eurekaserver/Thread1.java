package com.sunlong.cloud.eurekaserver;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author : shipp
 * @description :
 * @data : 2018/11/18 14:50
 */
public class Thread1 extends Thread {
    private Condition condition;

    private Lock lock;

    public Thread1(Lock lock, Condition condition) {
        this.condition = condition;
        this.lock = lock;
    }

    @Override
    public void run() {
//        lock.lock();
//        System.out.println("aaa");
////        try {
////            condition.await();
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        lock.unlock();

        try {

            lock.lock();
            System.out.println(111);
//            Thread.sleep(5000);
            condition.await();
//            System.out.println("wait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            System.out.println("finally");
            lock.unlock();
        }
        System.out.println(333);

    }
}
