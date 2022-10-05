package com.cdemo.dubbo.provider.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: star
 * @time: 2021/8/17 12:32
 */
@Slf4j
@Activate(group = {Constants.PROVIDER})
public class DubboLogFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.info("DubboLogFilter Begin: {}-{}-{}", invocation.getInvoker().getInterface(), invocation.getMethodName(), invocation.getArguments());
        final Result invoke = invoker.invoke(invocation);
        log.info("DubboLogFilter End: {}", invoke.getValue());
        return invoke;
    }
}
