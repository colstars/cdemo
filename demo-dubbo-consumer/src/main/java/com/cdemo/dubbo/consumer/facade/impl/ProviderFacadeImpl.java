package com.cdemo.dubbo.consumer.facade.impl;

import com.cdemo.dubbo.consumer.bean.ConsumerRequest;
import com.cdemo.dubbo.consumer.bean.ConsumerResponse;
import com.cdemo.dubbo.consumer.facade.ConsumerFacade;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: star
 * @time: 2021/8/16 20:13
 */
@Slf4j
public class ProviderFacadeImpl implements ConsumerFacade {

    @Override
    public ConsumerResponse consumer(ConsumerRequest req) {
        log.info("");
        return null;
    }
}
