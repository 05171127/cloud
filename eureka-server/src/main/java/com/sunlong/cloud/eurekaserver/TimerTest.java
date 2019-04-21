package com.sunlong.cloud.eurekaserver;

import lombok.Getter;
import lombok.Setter;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author : shipp
 * @description :
 * @data : 2019/3/4 11:44
 */

@Getter
@Setter
public class TimerTest {

    Timer timer = new Timer();

    private TimerTask task;

    public static void main(String[] args) throws InterruptedException {
        TimerTest test = new TimerTest();
        test.initTask();

        test.timer.schedule(test.task, 3000);
        Thread.sleep(4000);
        test.task.cancel();
        Thread.sleep(100);

        test.initTask();
        test.timer.schedule(test.task, 3000);
//        test.timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("123");
//            }
//        }, 6000);
    }

    private void initTask() {
        this.task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("123");
            }
        };
    }
}
