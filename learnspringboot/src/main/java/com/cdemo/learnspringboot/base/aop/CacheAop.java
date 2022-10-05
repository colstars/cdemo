package com.cdemo.learnspringboot.base.aop;

import com.cdemo.learnspringboot.base.annotation.CacheAnnotation;
import com.cdemo.learnspringboot.base.annotation.RemoveCacheAnnotation;
import com.cdemo.learnspringboot.config.redis.CfbsRedisCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: star
 * @time: 2021/3/7 8:59
 */
//@Component
//@Aspect
//@Order(2)  //使用 order对多个切面进行排序,参数越小，越在前
public class CacheAop {
    private final static Logger logger = LoggerFactory.getLogger(CacheAop.class);

//    @Autowired
//    private RedisTemplate redisTemplate;

    @Autowired
    private CfbsRedisCache cfbsRedisCache;


//
//    @Autowired
//    private RedisCache redisCache;

    /**
     * 八、@annotation(注解类型)--匹配当前执行方法持有指定注解的方法
     * 1、注解类型也必须是全限定类型名；
     */

    // 匹配 自定义注解标注的类----注解标注在接口的方法上不起作用
    /*    @Pointcut(value = "@annotation(com.cdemo.learnspringboot.base.annotation.CacheAnnotation)")
    public void logCacheAnnotation() {
    }*/

    //另外一种声明方式
    @Pointcut("@annotation(cacheAnnotation)")
    public void getCacheAnnotation(CacheAnnotation cacheAnnotation) {
    }

    //另外一种声明方式
    @Pointcut("@annotation(removeCacheAnnotation)")
    public void removeCacheAnnotation(RemoveCacheAnnotation removeCacheAnnotation) {
    }

    @Around("@annotation(cacheAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint, CacheAnnotation cacheAnnotation) throws Throwable {
        Object value = null;

        // 0-1、 当前方法上注解的内容
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getMethod(signature.getName(), signature.getMethod().getParameterTypes());

        logger.info("[Advice AOP Log begin], method is : {}, args is:{}", signature.getDeclaringType() + "." + signature.getName(), joinPoint.getArgs());

        String keyEl = cacheAnnotation.key();
        // 0-2、 前提条件：拿到作为key的依据- 解析springEL表达式
        // 创建解析器
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(keyEl);
        EvaluationContext context = new StandardEvaluationContext(); // 参数
        // 添加参数
        Object[] args = joinPoint.getArgs();
        DefaultParameterNameDiscoverer discover = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discover.getParameterNames(method);
        for (int i = 0; null != parameterNames && i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        if (null == expression.getValue(context)) {
            logger.error("get cache error, read from db");
            return joinPoint.proceed();
        }

        // 解析
        String key = getCacheKey(cacheAnnotation.app(), cacheAnnotation.module(), expression.getValue(context).toString());

        // 1、 判定缓存中是否存在
        value = cfbsRedisCache.get(key, method.getReturnType());
        if (value != null) {
            logger.info("get from cache: key is:{}, value is:{}", key, value);
            return value;
        }else{
            logger.info("get from cache no result: key is:{}", key);
        }

        // 2、不存在则执行方法
        value = joinPoint.proceed();

        // 3、 同步存储value到缓存。
        if (cacheAnnotation.ttl() > 0) {
            cfbsRedisCache.put(key, value, cacheAnnotation.ttl());
            logger.info("CacheAop set value with ttl success");
        } else {
            cfbsRedisCache.put(key, value);
            logger.info("CacheAop set value success");
        }

        logger.info("[Advice AOP Log end], method is : {}, ret is:{}", signature.getDeclaringType() + "." + signature.getName(), value);

        return value;
    }

    @Around("@annotation(removeCacheAnnotation)")
    public Object evict(ProceedingJoinPoint joinPoint, RemoveCacheAnnotation removeCacheAnnotation) throws Throwable {
        Object value = null;

        // 0-1、 当前方法上注解的内容
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getMethod(signature.getName(), signature.getMethod().getParameterTypes());

        logger.info("[Advice AOP Log begin], method is : {}, args is:{}", signature.getDeclaringType() + "." + signature.getName(), joinPoint.getArgs());

        String keyEl = removeCacheAnnotation.key();
        // 0-2、 前提条件：拿到作为key的依据- 解析springEL表达式
        // 创建解析器
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(keyEl);
        EvaluationContext context = new StandardEvaluationContext(); // 参数
        // 添加参数
        Object[] args = joinPoint.getArgs();
        DefaultParameterNameDiscoverer discover = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discover.getParameterNames(method);
        for (int i = 0; null != parameterNames && i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        if (null != expression.getValue(context)) {
            logger.info("del cache - get cache exist, ready to del");
            // 解析
            String key = getCacheKey(removeCacheAnnotation.app(), removeCacheAnnotation.module(), expression.getValue(context).toString());
            value = joinPoint.proceed();
            cfbsRedisCache.evict(key);
        }else{
            logger.info("del cache - get cache not exist");
        }


        logger.info("[Advice AOP Log end], method is : {}", signature.getDeclaringType() + "." + signature.getName());
        return value;
    }

    /**
     * 环绕通知
     */
    /*@Around("@annotation(cacheAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint, CacheAnnotation cacheAnnotation) throws Throwable {
        Object value = null;



        // 0-1、 当前方法上注解的内容
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getMethod(signature.getName(), signature.getMethod().getParameterTypes());

        logger.info("[Advice AOP Log begin], method is : {}, args is:{}", signature.getDeclaringType() + "." + signature.getName(), joinPoint.getArgs());

        String keyEl = cacheAnnotation.key();
        // 0-2、 前提条件：拿到作为key的依据- 解析springEL表达式
        // 创建解析器
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(keyEl);
        EvaluationContext context = new StandardEvaluationContext(); // 参数
        // 添加参数
        Object[] args = joinPoint.getArgs();
        DefaultParameterNameDiscoverer discover = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discover.getParameterNames(method);
        for (int i = 0; null != parameterNames && i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        if(null == expression.getValue(context)){
            logger.error("get cache error, read from db");
            return joinPoint.proceed();
        }

        // 解析
        String key = getCacheKey(cacheAnnotation, expression.getValue(context).toString());

        // 1、 判定缓存中是否存在
        value = redisTemplate.opsForValue().get(key);
        if (value != null) {
            logger.info("get from cache: key is:{}, value is:{}", key, value);
            return value;
        }

        // 2、不存在则执行方法
        value = joinPoint.proceed();

        // 3、 同步存储value到缓存。
        if(cacheAnnotation.ttl() > 0){
            redisTemplate.opsForValue().set(key, value, cacheAnnotation.ttl(), TimeUnit.SECONDS);
            logger.info("CacheAop set value with ttl success");
        }else{
            redisTemplate.opsForValue().set(key, value);
            logger.info("CacheAop set value success");
        }

        logger.info("[Advice AOP Log end], method is : {}, ret is:{}", signature.getDeclaringType() + "." + signature.getName(), value);

        return value;
    }*/
    private String getCacheKey(final String app, final String module, String key) {
        return String.format("%s:%s:%s", app, module, key);
    }
}
