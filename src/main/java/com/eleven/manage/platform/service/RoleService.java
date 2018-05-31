package com.eleven.manage.platform.service;

import com.eleven.manage.platform.dto.PageResponseDTO;
import com.eleven.manage.platform.dto.RoleDTO;
import com.eleven.manage.platform.dto.RolePermissionMapperDTO;

import java.util.List;

/**
 * @author ywl
 * @date 2018/05/25
 **/
public interface RoleService {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(java.lang.Integer id);

    /**
     * 更新
     * @param param
     * @return
     */
    int update(RoleDTO param);

    /**
     * 新增
     * @param param
     * @return
     */
    int insert(RoleDTO param);

    /**
     * 根据ID查询
     * @param param
     * @return
     */
    RoleDTO selectById(RoleDTO param);

    /**
     * 根据条件查询
     * @param param
     * @return
     */
    List<RoleDTO> selectByCondition(RoleDTO param);

    /**
     * 分页查询
     * @param param
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResponseDTO selectByPage(RoleDTO param, int pageNum, int pageSize);

    /**
     * 根据roleId查询权限列表
     * @param roleId
     * @return
     */
    List<RolePermissionMapperDTO> findPermissionsByRoleId(int roleId);

    /**
     * 保存角色与权限的关联
     * @param param
     */
    void saveRolePerMap(RolePermissionMapperDTO param);
}
