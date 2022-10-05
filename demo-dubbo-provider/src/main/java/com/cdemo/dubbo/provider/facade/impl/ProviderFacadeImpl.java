package com.cdemo.dubbo.provider.facade.impl;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import provider.bean.ProviderRequest;
import provider.bean.ProviderResponse;
import provider.facade.ProviderFacade;

/**
 * @description:
 * @author: star
 * @time: 2021/8/16 20:13
 */
@Slf4j
@Service
public class ProviderFacadeImpl implements ProviderFacade {
    @Override
    public ProviderResponse provider(ProviderRequest req) {
        log.info("provider,{}",req);
        final ProviderResponse providerResponse = new ProviderResponse();
        providerResponse.setResult(req.getName() + " is a good boy");
//
//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        int a = 1/0;
        return providerResponse;
    }
}
