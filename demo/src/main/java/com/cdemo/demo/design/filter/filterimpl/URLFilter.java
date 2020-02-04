package com.cdemo.demo.design.filter.filterimpl;

import com.cdemo.demo.design.filter.Filter;
import com.cdemo.demo.design.filter.Request;
import com.cdemo.demo.design.filter.Response;

public class URLFilter implements Filter {

    public void doFilter(Request r, Response s, Filter filter) {
        if (r.getReqMsg().contains("www")) {
            s.setRespMsg("请求中含有网址www，处理失败");
        } else {
            System.out.println("URLFilter begin");
            filter.doFilter(r, s, filter);
            System.out.println("URLFilter end response is " + s);
        }
    }
}
