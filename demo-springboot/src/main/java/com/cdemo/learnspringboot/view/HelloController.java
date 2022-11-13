package com.cdemo.learnspringboot.view;

import com.cdemo.dal.model.UserInfo;
import com.cdemo.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    private HelloService helloService;

    /**
     * @requestMapping = @GetMapping + @PostMapping
     * @author: col_star
     * @time: 2021/3/7 9:08
     */
    @GetMapping("/query")
    public String query(@RequestParam(value = "name", defaultValue = "World") String name) {

        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        final UserInfo userInfo1 = helloService.queryUser(userInfo);

        return String.format("Hello %s!", userInfo.getName());
    }

    @GetMapping("/querylist")
    public String queryList(@RequestParam(value = "name", defaultValue = "star") String name) {
        helloService.queryUserList(name);

        return "成功";
    }

    @GetMapping("/del")
    public String del(@RequestParam(value = "name", defaultValue = "World") String name) {

        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        helloService.delUserCache(userInfo);

        return "删除" + name;
    }

    @GetMapping("/delUserInfo")
    public String delUserInfo(@RequestParam(value = "name", defaultValue = "World") String name) {

        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        helloService.delUserCache(userInfo);

        return "删除" + name;
    }
}
