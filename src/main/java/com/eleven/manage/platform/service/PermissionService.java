package com.eleven.manage.platform.service;

import com.eleven.manage.platform.dto.common.PageResponseDTO;
import com.eleven.manage.platform.dto.basic.PermissionDTO;
import com.eleven.manage.platform.dto.basic.PermissionMenuMapperDTO;

import java.util.List;

/**
 * 权限服务
 * @author ywl
 * @date 2018/05/25
 **/
public interface PermissionService {
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
    int update(PermissionDTO param);

    /**
     * 新增
     * @param param
     * @return
     */
    int insert(PermissionDTO param);

    /**
     * 根据ID查询
     * @param param
     * @return
     */
    PermissionDTO selectById(PermissionDTO param);

    /**
     * 根据条件查询
     * @param param
     * @return
     */
    List<PermissionDTO> selectByCondition(PermissionDTO param);
    /**
     * 分页查询
     * @param param
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResponseDTO selectByPage(PermissionDTO param, int pageNum, int pageSize);

    /**
     * 根据ID查询关联菜单
     * @param id
     * @return
     */
    List<PermissionMenuMapperDTO> findMenusByPermission(int id);

    /**
     * 保存权限与菜单的关联
     * @param param
     */
    void savePerMenuMap(PermissionMenuMapperDTO param);
}
