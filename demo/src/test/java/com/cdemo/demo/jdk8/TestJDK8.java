package com.cdemo.demo.jdk8;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @description:
 * @author: Administrator
 * @date: Created in 2020/2/29 22:14
 */
public class TestJDK8 {
    @Test
    public void test1() {
        String bb = "bb";
        UserService userService = (a, b) -> {
            System.out.println("-------");
            return bb + a + b;
        };

//        System.out.println(userService.say("1", "b"));

        test2("test2", (a, b) -> {
            return new BigDecimal(1);
        });

    }

    private void test2(String name ,UserService userService){
        System.out.println(userService.say("a", "b"));
    }
}
