package com.cdemo.demo.disruptor.trans;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @description: 发布事件
 * 当用一个简单队列来发布事件的时候会牵涉更多的细节，这是因为事件对象还需要预先创建。
 * 发布事件最少需要两步：获取下一个事件槽并发布事件（发布事件的时候要使用try/finnally保证事件一定会被发布）。
 * 如果我们使用RingBuffer.next()获取一个事件槽，那么一定要发布对应的事件。
 * 如果不能发布事件，那么就会引起Disruptor状态的混乱。
 * 尤其是在多个事件生产者的情况下会导致事件消费者失速，从而不得不重启应用才能会恢复。
 * @create: 2019-04-14 22:36:46
 * @author: Mr.Yanxingxing
 */
public class TransEventProducer {
    private final RingBuffer<TransEvent> ringBuffer;

    public TransEventProducer(RingBuffer<TransEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }


    public void onData(ByteBuffer bb) {
        //ringBuffer看做一个事件队列，next得到下面一个事件槽
        long sequence = ringBuffer.next();
        try {
            //用上面的索引取出一个空的事件用于填充（获取该序号对应的事件对象）
            TransEvent event = ringBuffer.get(sequence);

            //获取要通过事件传递的业务数据
            TransBean transBean = new TransBean();
            transBean.setReqNo("请求流水号" + bb.getInt(0));
            transBean.setOrderNo("订单编号" + bb.getInt(0));

            event.setTransBean(transBean);
        } finally {
            //发布事件
            //注意，最后的 ringBuffer.publish 方法必须包含在 finally 中以确保必须得到调用；如果某个请求的 sequence 未被提交，将会堵塞后续的发布操作或者其它的 producer。
            ringBuffer.publish(sequence);
        }
    }
}

