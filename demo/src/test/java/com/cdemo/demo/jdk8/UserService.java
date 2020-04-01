package com.cdemo.demo.jdk8;

/**
 * @description:
 * @author: Administrator
 * @date: Created in 2020/2/29 22:16
 */
@FunctionalInterface
public interface UserService<T> {

    T say(String word, String position);
}
