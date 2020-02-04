package com.cdemo.demo.design.filter;

import com.cdemo.demo.design.filter.filterimpl.StringFilter;
import com.cdemo.demo.design.filter.filterimpl.URLFilter;

public class Client {

    public static void main(String[] args) {

        Request  req  = new Request();
        Response resp = new Response();

        req.setReqMsg("aaa");

        System.out.println(req);

        FilterChain filterChain = new FilterChain();
        filterChain.add(new StringFilter())
                .add(new URLFilter());
        filterChain.doFilter(req, resp, filterChain);

        System.out.println(resp);
    }
}
