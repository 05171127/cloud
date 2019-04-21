package com.sunlong.cloud.eurekaserver;

import java.util.concurrent.locks.Lock;

/**
 * @author : shipp
 * @description :
 * @data : 2018/11/18 15:31
 */
public class Thread3 extends Thread {
    private Lock lock;

    public Thread3(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        lock.unlock();
    }
}
