package com.cdemo.dubbo.consumer.bean;

import com.cdemo.dubbo.consumer.common.AbstractRequest;
import lombok.Data;

/**
 * @description:
 * @author: star
 * @time: 2021/8/16 20:14
 */
@Data
public class ConsumerRequest extends AbstractRequest {
    private String name;
}
