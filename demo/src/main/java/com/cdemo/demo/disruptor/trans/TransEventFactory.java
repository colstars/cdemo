package com.cdemo.demo.disruptor.trans;

import com.lmax.disruptor.EventFactory;

/**
 * @description: Disruptor为我们预先分配这些事件，我们需要一个EventFactory创建事件
 * @create: 2019-04-14 22:52:09
 * @author: Mr.Yanxingxing
 */
public class TransEventFactory implements EventFactory<TransEvent> {

    public TransEvent newInstance() {
        return new TransEvent(new TransBean());
    }
}
