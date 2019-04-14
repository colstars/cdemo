package com.cdemo.demo.lock.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 可重入锁，m1 调用 m2
 * getHoldCount 方法，获取当前锁被持有多少
 * @create: 2019-04-12 23:07:04
 * @author: Mr.Yanxingxing
 */
public class TestHoldCount {

    //重入锁
    private ReentrantLock lock = new ReentrantLock();

    public void m1() {
        try {
            lock.lock();
            System.out.println("进入m1方法，holdCount数为：" + lock.getHoldCount());

            //调用m2方法
            m2();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void m2() {
        try {
            lock.lock();
            System.out.println("进入m2方法，holdCount数为：" + lock.getHoldCount());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        TestHoldCount thc = new TestHoldCount();
        thc.m1();
    }
}
