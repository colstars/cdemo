package com.cdemo.demo.design.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter {
    private List<Filter> filters = new ArrayList<Filter>();
    private int index = 0;

    public FilterChain addFilter(Filter f) {
        this.filters.add(f);
        return this;
    }

    public <R, S> String doFilter(R r, S s, FilterChain filterChain) {
        if (index == filters.size()) {
            return null;
        }

        Filter f = filters.get(index);
        index++;

        f.doFilter(r, s, filterChain);

        return null;
    }
}
