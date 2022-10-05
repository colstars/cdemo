package com.cdemo.demo.concurrency;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: star
 * @time: 2021/8/19 18:53
 */
public class ThreadTest {
    private static final ScheduledExecutorService delayExportExecutor = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("DubboServiceDelayExporter", true));

    @Test
    public void test1(){

        long delay = 3;

        delayExportExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("11");
            }
        }, delay, TimeUnit.SECONDS);

    }

}
