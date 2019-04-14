package com.cdemo.demo.design.filter;

public interface Filter {
    <R, S> String doFilter(R r, S s, FilterChain filterChain);
}
