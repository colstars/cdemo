package com.cdemo.demo.jdk8;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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

    @Test
    public void test2(){
        Set<String> set = Collections.newSetFromMap(new ConcurrentHashMap<>(16));

        set.add("1");
        set.add("2");
        set.add("3");
        set.add("1");
        set.add("");
        set.add("");

        System.out.println(set);
    }
}
