package com.cdemo.demo.design.masterworker;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @description: 多个工人
 * @create: 2019-04-02 20:34:36
 * @author: Mr.Yanxingxing
 */
@Data
@Slf4j
public class Worker implements Runnable {

    /**
     * 获取任务队列，读取任务队列中的任务进行处理
     */
    private ConcurrentLinkedQueue<Task> workQueue;

    /**
     * 记录处理任务的结果
     */
    private ConcurrentHashMap<String, Object> resultMap;

    public void run() {
        while (true) {
            Task task = this.workQueue.poll();
            if (task != null) {
                log.info("线程开始{}开始处理任务{}", Thread.currentThread().getName(), task);
                resultMap.put(Thread.currentThread().getName(), task);
            }else {
                log.info("线程开始{}已经无法获取新的任务，关闭", Thread.currentThread().getName());
                break;
            }
        }
    }
}
