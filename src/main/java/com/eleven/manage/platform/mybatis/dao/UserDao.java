package com.eleven.manage.platform.mybatis.dao;

import com.eleven.manage.platform.mybatis.model.UserDO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DAO层
 * @author ywl
 * @date 2018/5/16
 **/
@Service("userDao")
public interface UserDao {
    int insert(UserDO record);
    List<UserDO> selectUsers();
}
