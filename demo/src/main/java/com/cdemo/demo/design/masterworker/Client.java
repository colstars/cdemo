package com.cdemo.demo.design.masterworker;

import java.util.Random;

public class Client {

    public static void main(String[] args) {

        //创建包工头，创建2个工人
        Master master = new Master(new Worker(), 5);

        //创建100个任务
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            master.submit(new Task(i, "task"+i, random.nextInt(10000)));
        }

        //包工头命令开始工作
        master.execute();

    }
}
