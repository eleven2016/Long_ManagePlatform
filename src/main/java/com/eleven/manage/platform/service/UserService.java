package com.eleven.manage.platform.service;

import com.eleven.manage.platform.mybatis.model.UserDO;

import java.util.List;

/**
 * @author ywl
 * @date 2018/5/16
 **/
public interface UserService {
    int addUser(UserDO user);

    List<UserDO> findAllUser(int pageNum, int pageSize);
}
