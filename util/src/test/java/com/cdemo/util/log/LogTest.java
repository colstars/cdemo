package com.cdemo.util.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LogTest {
    
    @Test
    public void test1() {
        log.info("haha");
    }

    public static void main(String[] args) {
        while (true){
            System.out.println(11);
            return;
        }
    }
}
