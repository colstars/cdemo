package com.cdemo.demo.disruptor.base;

import lombok.ToString;

import java.io.Serializable;

/**
 * @description: 声明事件
 * E 枚举具体的事件体
 * @create: 2019-04-13 23:52:45
 * @author: Mr.Yanxingxing
 */
@ToString
public abstract class DisruptorEvent<E> implements Serializable {
    protected E event;

    public DisruptorEvent(E event) {
        this.event = event;
    }


}
