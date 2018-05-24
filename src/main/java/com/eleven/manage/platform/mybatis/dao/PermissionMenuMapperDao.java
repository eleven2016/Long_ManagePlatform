package com.eleven.manage.platform.mybatis.dao;

import com.eleven.manage.platform.mybatis.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author ywl
 * @date 2018/05/24
 */
@Service("permissionMenuMapperDao")
public interface PermissionMenuMapperDao{
    public int deleteById (java.lang.Integer id);

    public int update (PermissionMenuMapperDO param);

    public int insert (PermissionMenuMapperDO param);

    public List<PermissionMenuMapperDO> selectById(PermissionMenuMapperDO param);

    public List<PermissionMenuMapperDO> selectByCondition(PermissionMenuMapperDO param);

}