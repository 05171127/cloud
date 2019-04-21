package com.sunlong.cloud.eurekaserver;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 数据接收器
 * 酒店目前的查询是开线程，遍历所有的供应商，在对供应商的返回结果合并，后再返回给前端
 * 当供应商多起来了，或者供应商的响应比较慢的时候，前端的响应很慢，非常影响用户的体验
 * 所以改为分步返回，只要有一个供应商返回后，就给前端响应，同时继续接收其他供应商的返回并合并，以便供后续响应
 * @author : shipp
 * @data : 2018/11/14 15:10
 */
public class AgentDataAcceptor {

    // 总共多少次返回
    private int totalCnt;

    // 已经接收多少
    private AtomicInteger acceptCnt = new AtomicInteger(0);

    // 进度
    private double percentage = 0.00;

    // 是否是第一次收到 第一次收到立即计算并返回
    private boolean firstReceived = true;

    // 定时任务，定时合并数据，避免每次有返回都合并
    private TimerTask task;

    // 定时任务的间隔时间 每隔多少秒去尝试合并
    private int interval;

    // 重入锁
    Lock lock = new ReentrantLock();

    // timer
    Timer timer = new Timer();

    // 是否有变动
    private boolean changed = false;

    // 业务数据list 用来保存数据 这个地方没有什么并发 直接用lock锁
    private List<Integer> list = new ArrayList<>();

    private int total;

    public int getTotalCnt() {
        return totalCnt;
    }

    public AtomicInteger getAcceptCnt() {
        return acceptCnt;
    }

    public double getPercentage() {
        return percentage;
    }

    public int getInterval() {
        return interval;
    }

    public boolean isChanged() {
        return changed;
    }

    public List<Integer> getList() {
        return list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public void setAcceptCnt(AtomicInteger acceptCnt) {
        this.acceptCnt = acceptCnt;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void setFirstReceived(boolean firstReceived) {
        this.firstReceived = firstReceived;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * constructor to init totalCnt and timerTask
     * @author shipp
     * @date 2018/11/14 20:16
     * @param count
     * @return 
     */
    public AgentDataAcceptor(int count) {
        this.totalCnt = count;
        this.task = new TimerTask() {
            public void run() {
                if (changed) {
                    lock.lock();
                    calcResult();
                    lock.unlock();
                }
            }
        };
        this.total = 0;
    }

    /**
     * 计算结果 主业务处理就在这
     * @author shipp
     * @date 2018/11/14 20:16
     * @param 
     * @return void
     */
    private void calcResult() {
        this.acceptCnt.addAndGet(list.size());
        list.forEach(p -> total += p);
        percentage = new BigDecimal((double) acceptCnt.get() / totalCnt).setScale(2, RoundingMode.HALF_UP).doubleValue();
        list.clear();
        changed = false;
    }

    /**
     * 供应商成功响应之后，把返回结果放到结果集中，如果是第一次请求，则需要默认处理一次结果
     * @author shipp
     * @date 2018/11/14 20:17
     * @param ret
     * @return void
     */
    public void success(int ret) {
        lock.lock();
        this.changed = true;
        this.list.add(ret);
        if (this.firstReceived) {
            this.firstReceived = false;
            calcResult();
            timer.schedule(task,3000,3000);
        }
        lock.unlock();
    }

    /**
     * 供应商返回失败
     * @author shipp
     * @date 2018/11/14 20:18
     * @param
     * @return void
     */
    public void failed() {
        this.acceptCnt.incrementAndGet();
        this.setChanged(true);
    }
}
