package com.cdemo.demo.disruptor.trans;

import com.cdemo.demo.disruptor.base.DisruptorEvent;
import com.cdemo.demo.disruptor.base.DisruptorEventHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 处理交易事件的消费者
 * @create: 2019-04-14 00:09:35
 * @author: Mr.Yanxingxing
 */
@Slf4j
public class TransEventHandler implements DisruptorEventHandler {

    public void onEvent(DisruptorEvent event, long sequence, boolean endOfBatch) throws Exception {
        Thread.sleep(500);
        log.info("开始处理交易流程:{}", event);
    }
}
