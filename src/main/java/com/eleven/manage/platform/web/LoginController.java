package com.eleven.manage.platform.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.eleven.manage.platform.dto.LoginDTO;
import com.eleven.manage.platform.dto.ResponseDTO;
import com.eleven.manage.platform.dto.UserDTO;
import com.eleven.manage.platform.service.LoginService;


/**
 * 登录controller
 * @author ywl
 * @date 2018/5/21
 **/
@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;
    /**
     * 登录验证
     * @param loginDTO
     * @return
     */
    @PostMapping("/checkLogin")
    @ResponseBody
    public ResponseDTO checkLogin(@RequestBody LoginDTO loginDTO){
        logger.info("登录参数"+ JSON.toJSONString(loginDTO));
        ResponseDTO responseDTO =new ResponseDTO();
        try{
            String token = loginService.checkLogin(loginDTO);
            responseDTO.setSuccess(true);
            responseDTO.setData(token);
        }catch (IllegalArgumentException e){
            responseDTO.setSuccess(false);
            responseDTO.setErrorMessage(e.getMessage());
        }catch (Exception e){
            logger.error("系统错误", e);
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }

    /**
     * 登陆成功跳转
     * @return
     */
    @GetMapping("/getUserByToken")
    @ResponseBody
    public ResponseDTO getUserByToken(@RequestParam(name = "token") String token){
        logger.info("登录参数"+ token);
        ResponseDTO responseDTO =new ResponseDTO();
        try{
            UserDTO userDTO= loginService.getUserByToken(token);
            responseDTO.setSuccess(true);
            responseDTO.setData(userDTO);
        }catch (IllegalArgumentException e){
            responseDTO.setSuccess(false);
            responseDTO.setErrorMessage(e.getMessage());
        }catch (Exception e){
            logger.error("系统错误", e);
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }
}
