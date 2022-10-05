package com.cdemo.dal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @description:
 * @author: star
 * @time: 2021/3/7 9:00
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserInfo {
    private int id;
    private String name;
    private int age;

    private int startPage;
    private int endPage;
}
