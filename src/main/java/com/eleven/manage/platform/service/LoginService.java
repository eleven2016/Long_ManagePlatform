package com.eleven.manage.platform.service;

import com.eleven.manage.platform.dto.common.LoginDTO;
import com.eleven.manage.platform.dto.basic.UserDTO;

/**
 * @author ywl
 * @date 2018/5/31
 **/
public interface LoginService {

    /**
     * 验证登录
     * @param loginDTO
     * @return
     */
    String checkLogin(LoginDTO loginDTO);

    /**
     * 根据TOKEN
     * @param token
     * @return
     */
    UserDTO getUserByToken(String token);
}
