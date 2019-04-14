package com.cdemo.demo.lock.reentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

@Slf4j
public class UseReentrantReadWriteLock {
    private ReentrantReadWriteLock rwLock    = new ReentrantReadWriteLock();
    private ReadLock               readLock  = rwLock.readLock();
    private WriteLock              writeLock = rwLock.writeLock();

    public void read() {
        try {
            readLock.lock();
            log.info("当前线程进入read...");
            Thread.sleep(3000);
            log.info("当前线程退出read...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public void write() {
        try {
            writeLock.lock();
            log.info("当前线程进入write...");
            Thread.sleep(3000);
            log.info("当前线程退出write...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {

        final UseReentrantReadWriteLock urrw = new UseReentrantReadWriteLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.read();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.read();
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.write();
            }
        }, "t3");
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.write();
            }
        }, "t4");

        // t1.start();
        // t2.start();

		// t1.start(); // R
		// t3.start(); // W

       t3.start();
       t4.start();


    }
}
