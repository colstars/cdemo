package com.cdemo.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import provider.bean.ProviderRequest;
import provider.bean.ProviderResponse;
import provider.facade.ProviderFacade;

/**
 * @description:
 * @author: star
 * @time: 2021/8/16 20:27
 */
@RestController
@Slf4j
public class ViewController {

    @Reference
    private ProviderFacade providerFacade;

    /**
     * @requestMapping = @GetMapping + @PostMapping
     * @author: col_star
     * @time: 2021/3/7 9:08
     */
    @GetMapping("/query")
    public String query(@RequestParam(value = "name", defaultValue = "World") String name) {
        log.info("query:{}", name);

        ProviderRequest request = new ProviderRequest();
        request.setName(name);
        final ProviderResponse provider = providerFacade.provider(request);
        log.info("return : {}", provider);


        return String.format("Hello %s!", name);
    }
}
