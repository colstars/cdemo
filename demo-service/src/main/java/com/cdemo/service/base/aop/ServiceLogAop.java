package com.cdemo.service.base.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: star
 * @time: 2021/3/7 9:19
 */
@Component
@Aspect
@Order(1)  //使用 order对多个切面进行排序,参数越小，越在前
public class ServiceLogAop {
    private final static Logger logger = LoggerFactory.getLogger(ServiceLogAop.class);

    /**
     * 一、execution(方法表达式)
     * 1、匹配方法时，只能匹配到实现类，匹配到接口类不能成功
     * 2、匹配方法执行
     */

    // 匹配指定包包及子包下的任何方法执行
//    @Pointcut(value = "execution(* com.cdemo.service.*(..))")
    public void logExecution1() {
    }


    /**
     * 二、within(类型表达式)
     * * 1、匹配类型时，只能匹配到实现类，匹配到接口类不能成功
     * * 2、匹配指定类型内的方法执行；
     */

    //匹配指定类型内的方法执行--只能匹配类型
//    @Pointcut(value = "within(com.cdemo.service.impl.HelloServiceImpl)")
    public void logWith1() {
    }

    //匹配指定类型内的方法执行(包下所有的类)
    @Pointcut(value = "within(com.cdemo.service.impl.*)")
    public void logWith2() {
    }


    /**
     * 环绕通知：包围一个连接点（join point）的通知
     * 可以实现改变方法参数并执行方法
     * @param pjp
     * @return
     */
    /**
     * 切入点使用
     *
     * @author: col_star
     * @time: 2021/3/7 9:44
     */
    @Around(value = "logWith2()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        Object obj = null;
        try {
            logger.info("[Advice Service Log begin], method is : {}, args is:{}", pjp.getSignature().getDeclaringType() + "." + pjp.getSignature().getName(), pjp.getArgs());
            obj = pjp.proceed();
            logger.info("[Advice Service Log end], resp is {}", obj);
        } catch (Throwable e) {
            logger.error("[Advice Service Log error], method is : {}, args is:{}", pjp.getSignature().getDeclaringType() + "." + pjp.getSignature().getName(), pjp.getArgs(), e);
        }
        return obj;
    }
}
