package com.eleven.manage.platform.mybatis.dao;

import com.eleven.manage.platform.mybatis.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author ywl
 * @date 2018/05/18
 */
@Service("userDao")
public interface UserDao{
    int deleteById (java.lang.Integer id);

    int update (UserDO param);

    int insert (UserDO param);

    List<UserDO> selectById(UserDO param);

    List<UserDO> selectByCondition(UserDO param);

}