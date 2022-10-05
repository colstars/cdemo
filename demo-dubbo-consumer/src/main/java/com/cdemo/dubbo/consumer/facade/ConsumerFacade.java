package com.cdemo.dubbo.consumer.facade;


import com.cdemo.dubbo.consumer.bean.ConsumerRequest;
import com.cdemo.dubbo.consumer.bean.ConsumerResponse;

import javax.jws.WebService;

@WebService
public interface ConsumerFacade {
    public ConsumerResponse consumer(ConsumerRequest req);
}
