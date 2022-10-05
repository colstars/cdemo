package com.cdemo.util.log;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @description:
 * @author: star
 * @time: 2021/8/13 23:04
 */
public class JULTest {

    static Logger logger = Logger.getLogger("com.cdemo.util.log.JULTest");

    static{
        logger.info("初始化 JULTest");
        final InputStream resourceAsStream = JULTest.class.getClassLoader().getResourceAsStream("logging.properties");
        final LogManager logManager = LogManager.getLogManager();

        try {
            logManager.readConfiguration(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        logger.info("jultest");
        logger.config("jultest22");
    }
}
