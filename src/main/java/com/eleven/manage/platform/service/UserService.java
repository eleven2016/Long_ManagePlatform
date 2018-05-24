package com.eleven.manage.platform.service;

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
     * @param userDTO
     * @return
     */
    int updateUser(UserDTO userDTO);

    /**
     * 删除用户
     * @param userDTO
     * @return
     */
    int deleteUser(UserDTO userDTO);

    /**
     * 按条件分页查询用户
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UserDTO> findByPage(UserDTO userDTO, int pageNum, int pageSize);

    /**
     * 按条件查询用户
     * @param userDTO
     * @return
     */
    List<UserDTO> findByCondition(UserDTO userDTO);
}
