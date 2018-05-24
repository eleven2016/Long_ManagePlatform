package com.eleven.manage.platform.mybatis.dao;

import com.eleven.manage.platform.mybatis.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author ywl
 * @date 2018/05/24
 */
@Service("userRoleMapperDao")
public interface UserRoleMapperDao{
    public int deleteById (java.lang.Integer id);

    public int update (UserRoleMapperDO param);

    public int insert (UserRoleMapperDO param);

    public List<UserRoleMapperDO> selectById(UserRoleMapperDO param);

    public List<UserRoleMapperDO> selectByCondition(UserRoleMapperDO param);

}