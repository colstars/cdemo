package com.cdemo.service.impl;

import com.cdemo.dal.dao.UserDao;
import com.cdemo.dal.daoslave.UserSlaveDao;
import com.cdemo.dal.model.UserInfo;
import com.cdemo.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: star
 * @time: 2021/3/7 9:04
 */
@Service
public class HelloServiceImpl implements HelloService {

    private final static Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserSlaveDao userSlaveDao;

    @Override
    public String queryUserList(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("startPage", 0);
        map.put("endPage", 5);

        final List<UserInfo> userInfos = userDao.findByName(map);
        System.out.println("queryUserInfo" + userInfos);

        final List<UserInfo> userSlaveInfos = userSlaveDao.findByName(map);
        System.out.println("queryUserSlaveInfo" + userSlaveInfos);

        return "";
    }

    @Override
    public UserInfo queryUser(UserInfo userInfo) {
        userInfo.setName(userInfo.getName());
        userInfo.setId(1);
        userInfo.setAge(20);
        return userInfo;
    }

    @Override
    public String delUserCache(UserInfo userInfo) {
        return null;
    }

    @Override
    public UserInfo delUserCache(String name) {
        UserInfo u = new UserInfo();
        u.setName(name);
        return u;
    }

    private UserInfo generateUser(String name) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        return userInfo;
    }
}
