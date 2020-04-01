package com.cdemo.demo.fee;

import com.cdemo.util.date.DateUtils;

import java.util.*;

/**
 * @description:
 * @author: Administrator
 * @date: Created in 2020/4/1 9:19
 */
public class Client {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("2020-03-11 22:10:18");
        list.add("2020-03-12 22:08:56");
        list.add("2020-03-16 22:09:51");
        list.add("2020-03-17 22:07:19");
        list.add("2020-03-18 22:07:34");
        list.add("2020-03-19 22:04:50");
        list.add("2020-03-23 22:12:37");
        list.add("2020-03-30 22:06:03");
        list.add("2020-03-31 22:19:23");

        int total = 0;
        for (String s : list){
            Date date = DateUtils.strToDate(s, "yyyy-MM-dd HH:mm:ss");
            Random random = new Random();
            int add = random.nextInt(5);
            int add2 = random.nextInt(5);
            int result = (112 + add + add2);
            System.out.println(DateUtils.addMinutes(date, 5 + add) + "   "  + result);

            total += result;
        }
        System.out.println(total);

    }
}
