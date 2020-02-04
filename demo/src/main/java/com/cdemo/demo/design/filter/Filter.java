package com.cdemo.demo.design.filter;

/**
 * @description: 责任链模式
 * @create: 2019-12-03 10:55:18
 * @author: Mr.Yanxingxing
 */
public interface Filter{

    /**
     * @description: r 模拟请求
     * s 模拟返回
     * @param: [r, s, filterChain]
     * @return: java.lang.String
     * @create: 2019-12-03 10:56:07
     * @author: Mr.Yanxingxing
     */
    void doFilter(Request r, Response s, Filter filter);
}
