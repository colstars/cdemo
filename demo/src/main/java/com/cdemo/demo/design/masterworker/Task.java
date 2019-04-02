package com.cdemo.demo.design.masterworker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @description: 任务类
 * @create: 2019-04-02 20:34:15
 * @author: Mr.Yanxingxing
 */
@Data
@ToString
@AllArgsConstructor
public class Task {

    private int id;
    private String name;
    private int price;
}
