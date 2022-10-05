package com.cdemo.dal.daoslave;

import com.cdemo.dal.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserSlaveDao {

    int insertUser(@Param("user") UserInfo user);

    /**
     * String name, int startPage , int endPage
     *
     * @author: col_star
     * @time: 2021/5/14 0:35
     */
    List<UserInfo> findByName(Map<String, Object> map);
}
