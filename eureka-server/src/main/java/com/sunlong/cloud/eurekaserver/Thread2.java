package com.sunlong.cloud.eurekaserver;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author : shipp
 * @description :
 * @data : 2018/11/18 14:51
 */
public class Thread2 extends Thread{
    private Condition condition;

    private Lock lock;

    public Thread2(Lock lock, Condition condition) {
        this.condition = condition;
        this.lock = lock;
    }

    @Override
    public void run() {
        int random = new Random().nextInt(5000);
//        System.out.println("signal start");
            try {
                Thread.sleep(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println(random + " 开始");
        lock.lock();
//        System.out.println(this.wait + " 开始");
//        try {
//            Thread.sleep(this.wait);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        condition.signal();
        lock.unlock();

        System.out.println(random + " 结束");
    }
}
