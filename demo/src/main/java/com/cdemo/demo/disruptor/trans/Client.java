package com.cdemo.demo.disruptor.trans;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 验证
 * @create: 2019-04-14 00:12:27
 * @author: Mr.Yanxingxing
 */
public class Client {

    public static void main(String[] args) {

        /**
         * 创建一个disruptor 实例
         *      * @param eventFactory   the factory to create events in the ring buffer.
         *      * @param ringBufferSize the size of the ring buffer, must be power of 2.
         *      * @param threadFactory  a {@link ThreadFactory} to create threads for processors.
         *      * @param producerType   the claim strategy to use for the ring buffer.
         *      * @param waitStrategy   the wait strategy to use for the ring buffer.
         */

        EventFactory eventFactory = new EventFactory<TransEvent>() {
            public TransEvent newInstance() {
                return new TransEvent(new TransBean());
            }
        };
        int ringBufferSize = 32 * 32; //
        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicInteger index = new AtomicInteger(1);

            public Thread newThread(Runnable r) {
                return new Thread((ThreadGroup) null, r, "disruptor-thread-" + index.getAndIncrement());
            }
        };

        //创建出disruptor
        Disruptor<TransEvent> disruptor = new Disruptor<TransEvent>(eventFactory, ringBufferSize, threadFactory, ProducerType.SINGLE, new YieldingWaitStrategy());

        //设置handler
        disruptor.handleEventsWith(new TransEventHandler());

        //启动
        disruptor.start();


        //发布交易事件
        RingBuffer<TransEvent> ringBuffer = disruptor.getRingBuffer();

        TransEventProducer transEventProducer = new TransEventProducer(ringBuffer);
        ByteBuffer         byteBuffer         = ByteBuffer.allocate(8);
        for (int i = 1; i <= 10; i++) {
            byteBuffer.putInt(0, i);
            transEventProducer.onData(byteBuffer);
        }

        disruptor.shutdown();//关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
    }
}
