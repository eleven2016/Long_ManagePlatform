package com.eleven.manage.platform.service;

import com.eleven.manage.platform.dto.MenuDTO;
import com.eleven.manage.platform.dto.PageResponseDTO;

import java.util.List;

/** 菜单服务
 * @author ywl
 * @date 2018/05/25
 **/
public interface MenuService {
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
    int update(MenuDTO param);

    /**
     * 新增
     * @param param
     * @return
     */
    int insert(MenuDTO param);

    /**
     * 根据ID查询
     * @param param
     * @return
     */
    MenuDTO selectById(MenuDTO param);

    /**
     * 根据条件查询
     * @param param
     * @return
     */
    List<MenuDTO> selectByCondition(MenuDTO param);

    /**
     * 分页查询
     * @param param
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResponseDTO selectByPage(MenuDTO param, int pageNum, int pageSize);

    /**
     * 根据用户查询该用户权限下的菜单
     * @param userId
     * @return
     */
    List<MenuDTO> selectByUser(int userId);
}
