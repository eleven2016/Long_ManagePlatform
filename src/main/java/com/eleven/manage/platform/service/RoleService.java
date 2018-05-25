package com.eleven.manage.platform.service;

import com.eleven.manage.platform.dto.RoleDTO;

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
}
