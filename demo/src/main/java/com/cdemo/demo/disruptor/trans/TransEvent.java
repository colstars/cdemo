package com.cdemo.demo.disruptor.trans;

import com.cdemo.demo.disruptor.base.DisruptorEvent;
import lombok.Data;
import lombok.ToString;

/**
 * @description: 交易事件
 * @create: 2019-04-14 00:02:38
 * @author: Mr.Yanxingxing
 */
@Data
@ToString
public class TransEvent extends DisruptorEvent<TransBean> {

    public TransBean transBean;

    public TransEvent(TransBean event) {
        super(event);
        this.transBean = event;
    }
}
