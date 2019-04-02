package com.cdemo.demo.design.masterworker;

public class Client {

    public static void main(String[] args) {

        //创建包工头，创建2个工人
        Master master = new Master(new Worker(), 2);

        //创建10个任务
        master.submit(new Task(1, "star1", 10000));
        master.submit(new Task(2, "star2", 20000));
        master.submit(new Task(3, "star3", 30000));
        master.submit(new Task(4, "star4", 40000));
        master.submit(new Task(5, "star5", 50000));
        master.submit(new Task(6, "star6", 60000));
        master.submit(new Task(7, "star7", 70000));
        master.submit(new Task(8, "star8", 80000));
        master.submit(new Task(9, "star9", 90000));
        master.submit(new Task(10, "star10", 100000));

        //包工头命令开始工作
        master.execute();

    }
}
