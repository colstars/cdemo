package com.cdemo.demo.disruptor.trans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @description: 交易实体
 * @create: 2019-04-13 23:55:28
 * @author: Mr.Yanxingxing
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransBean {
    private String     reqNo;   //请求流水号
    private String     orderNo; //订单编号
    private BigDecimal totalNum;//订单总金额

}
