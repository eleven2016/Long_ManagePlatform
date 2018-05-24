package com.eleven.manage.platform.mybatis.dao;

import com.eleven.manage.platform.mybatis.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author ywl
 * @date 2018/05/24
 */
@Service("permissionDao")
public interface PermissionDao{
    public int deleteById (java.lang.Integer id);

    public int update (PermissionDO param);

    public int insert (PermissionDO param);

    public List<PermissionDO> selectById(PermissionDO param);

    public List<PermissionDO> selectByCondition(PermissionDO param);

}