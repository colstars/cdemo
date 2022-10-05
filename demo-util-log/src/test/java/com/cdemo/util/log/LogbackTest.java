package com.cdemo.util.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LogbackTest {
    
    @Test
    public void test1() {
        log.info("haha");
        log.debug("111");
    }

}
