package com.eleven.manage.platform.service;

import com.eleven.manage.platform.dto.PageResponseDTO;
import com.eleven.manage.platform.dto.UserDTO;

import java.util.List;

/**
 * @author ywl
 * @date 2018/5/16
 **/
public interface UserService {
    /**
     * 添加用户
     * @param userDTO
     * @return
     */
    int addUser(UserDTO userDTO);

    /**
     * 更新用户
     * @param id
     * @return
     */
    int updateUser(UserDTO userDTO);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUser(int id);

    /**
     * 按条件分页查询用户
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResponseDTO findByPage(UserDTO userDTO, int pageNum, int pageSize);

    /**
     * 按条件查询用户
     * @param userDTO
     * @return
     */
    List<UserDTO> findByCondition(UserDTO userDTO);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    UserDTO findById(int id);
}
