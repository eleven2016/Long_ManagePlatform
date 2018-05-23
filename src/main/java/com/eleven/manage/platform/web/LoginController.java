package com.eleven.manage.platform.web;

import com.alibaba.fastjson.JSON;
import com.eleven.manage.platform.dto.LoginDTO;
import com.eleven.manage.platform.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 登录controller
 * @author ywl
 * @date 2018/5/21
 **/
@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
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
        responseDTO.setSuccess(true);
        return responseDTO;
    }

    /**
     * 登陆成功跳转
     * @return
     */
    @GetMapping("/loginSuccess")
    public String loginSuccess(){
        return "common/mainboard";
    }
}
