package com.cdemo.demo.design.filter.filterimpl;

import com.cdemo.demo.design.filter.Filter;
import com.cdemo.demo.design.filter.Request;
import com.cdemo.demo.design.filter.Response;


public class StringFilter implements Filter {
    public void doFilter(Request r, Response s, Filter filter) {
        if (r.getReqMsg().contains("a")) {
            s.setRespMsg("请求中含有字母a，处理失败");
        } else {
            System.out.println("StringFilter begin");
            filter.doFilter(r, s, filter);
            System.out.println("StringFilter end , response is " + s);
        }
    }
}
