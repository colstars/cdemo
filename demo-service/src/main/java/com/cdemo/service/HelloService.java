package com.cdemo.service;


import com.cdemo.dal.model.UserInfo;

public interface HelloService {
    String queryUserList(String name);

    UserInfo queryUser(UserInfo userInfo);

    String delUserCache(UserInfo userInfo);

    UserInfo delUserCache(String name);
}
