package com.cdemo.demo.design.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 责任链模式中的链
 * @create: 2019-12-03 10:57:36
 * @author: Mr.Yanxingxing
 */
public class FilterChain implements Filter {
    //过滤器列表
    private List<Filter> filters = new ArrayList<Filter>();

    //当前过滤器
    private int index = 0;

    public FilterChain add(Filter f) {
        this.filters.add(f);
        return this;
    }

    public void doFilter(Request r, Response s, Filter filter) {
        if (index == filters.size()) {
            s.setRespMsg("处理成功");
            return;
        }

        Filter f = filters.get(index);
        index++;
        f.doFilter(r,s,filter);
    }
}
