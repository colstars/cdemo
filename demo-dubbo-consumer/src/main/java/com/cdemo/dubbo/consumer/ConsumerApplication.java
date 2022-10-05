package com.cdemo.dubbo.consumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/**
 * SpringBootApplication 注解的类一定要放在自定义包下且属于自定义包的
 * SpringBootApplication是一个方便的注解，增加了所有的以下内容：
 * Configuration 标记一个类来作为bean定义的应用程序上下文的资源
 * EnableAutoConfiguration告诉Spring Boot开始加载基于类路径下的配置信息、beans、各种属性配置。
 * ComponentScan 告诉Spring寻找其他组件，配置，以及业务层类,最前面才能加载到所有的类。
 * ImportResource
 * 默认加载："classpath:application.properties"，项目中只能包含1个该文件
 * 默认加载：“classpath:logback-spring.xml”
 * PropertySource
 * 加载properties文件
 *
 * @author: col_star
 * @time: 2021/3/7 8:55
 */

@SpringBootApplication(scanBasePackages = {"com.cdemo.dubbo.consumer"})
@Slf4j
@EnableDubbo
public class ConsumerApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ConsumerApplication.class, args);

/*        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        Arrays.stream(beanNames).forEach(System.out::println);*/
    }

}
