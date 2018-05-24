package com.eleven.manage.platform.mybatis.dao;

import com.eleven.manage.platform.mybatis.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author ywl
 * @date 2018/05/24
 */
@Service("rolePermissionMapperDao")
public interface RolePermissionMapperDao{
    public int deleteById (java.lang.Integer id);

    public int update (RolePermissionMapperDO param);

    public int insert (RolePermissionMapperDO param);

    public List<RolePermissionMapperDO> selectById(RolePermissionMapperDO param);

    public List<RolePermissionMapperDO> selectByCondition(RolePermissionMapperDO param);

}