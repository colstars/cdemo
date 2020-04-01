package com.cdemo.util.ticket;

import org.joda.time.DateTime;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Administrator
 * @date: Created in 2020/3/2 11:07
 */
public class GenerateTicket {

    @Test
    public void test1(){
        List<String> list = new ArrayList<String>();
        list.add("2020/01/06 22:26:56");
        list.add("2020/01/07 22:08:36");
        list.add("2020/01/09 22:11:27");
        list.add("2020/01/10 22:07:59");
        list.add("2020/01/13 22:10:11");
        list.add("2020/01/14 22:05:11");
        list.add("2020/01/15 22:44:23");
        list.add("2020/01/17 22:07:29");

        for(String temp : list){
            DateTime dateTime = new DateTime(temp);
            System.out.println(dateTime);
        }
    }
}
