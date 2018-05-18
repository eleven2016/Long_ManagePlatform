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
    public int deleteById (java.lang.Integer id);

    public int update (UserDO param);

    public int insert (UserDO param);

    public List<UserDO> selectById(UserDO param);

    public List<UserDO> selectByCondition(UserDO param);

}