package com.cdemo.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Administrator
 * @date: Created in 2020/2/19 11:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    public String name;
    public int age;
    public String sex;
    public String memo;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
