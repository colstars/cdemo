package com.cdemo.demo.design.masterworker;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @description: 一个主人
 * @create: 2019-04-02 20:16:52
 * @author: Mr.Yanxingxing
 */
@Slf4j
public class Master {

    //所有的任务都在这个队列集合中
    private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<Task>();

    //包含所有的工人集合
    private Map<String, Thread> workers = new HashMap<String, Thread>();

    //记录所有工人对应的工作结果
    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();

    //构造方法创建工人集合
    public Master(Worker worker, int workerNum) {
        //将任务队列和工作结果set到worker中
        worker.setWorkQueue(this.workQueue);
        worker.setResultMap(this.resultMap);

        for (int i = 1; i <= workerNum; i++) {
            workers.put("工人" + i + "号", new Thread(worker));
        }
    }

    /**
     * @description: 提交任务队列
     * @param: [task]
     * @return: void
     * @create: 2019-04-02 20:48:58
     * @author: Mr.Yanxingxing
     */
    public void submit(Task task) {
        this.workQueue.add(task);
    }

    public void execute() {
        for (Map.Entry<String, Thread> entry : workers.entrySet()) {
            log.info("worker: {} 开始启动", entry.getKey());
            entry.getValue().start();

        }
    }

}
