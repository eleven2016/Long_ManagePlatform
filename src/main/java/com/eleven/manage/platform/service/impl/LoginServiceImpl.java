package com.eleven.manage.platform.service.impl;

import com.eleven.manage.platform.dto.common.LoginDTO;
import com.eleven.manage.platform.dto.basic.UserDTO;
import com.eleven.manage.platform.mybatis.dao.UserDao;
import com.eleven.manage.platform.mybatis.model.UserDO;
import com.eleven.manage.platform.redis.RedisService;
import com.eleven.manage.platform.service.LoginService;
import com.eleven.manage.platform.utils.EncryptUtil;
import com.eleven.manage.platform.utils.GenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author ywl
 * @date 2018/5/31
 **/
@Service("loginService")
public class LoginServiceImpl implements LoginService {
    GenerateUtil<UserDO, UserDTO> userGenerateUtil     = new GenerateUtil<>();

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService;

    /**
     * 验证登录
     * @param loginDTO
     * @return
     */
    @Override
    public String checkLogin(LoginDTO loginDTO) {
        String token ="";

        Assert.notNull(loginDTO,"请输入用户名/密码");
        Assert.hasText(loginDTO.getUserName(),"请输入用户名");
        Assert.hasText(loginDTO.getPassword(),"请输入密码");
        UserDO param =new UserDO();
        param.setAccount(loginDTO.getUserName());
        List<UserDO> users = userDao.selectByCondition(param);
        Assert.notEmpty(users,"未找到该用户!");
        Assert.isTrue(users.size() == 1,"账号重复!");
        String password = EncryptUtil.md5Encode(loginDTO.getPassword());
        Assert.isTrue(password.equals(users.get(0).getPassword()),"账号重复!");

        //验证成功,生成TOKEN

        token =EncryptUtil.aes_encrypt(users.get(0).getId()+"-"+users.get(0).getUserName());
        redisService.set(token,token,Long.valueOf(3600));
        return  token;
    }

    @Override
    public UserDTO getUserByToken(String token) {

        Assert.hasText(token,"请重新登录");
        UserDTO user = null;
        try{
            Object tokenValue = redisService.get(token);
            Assert.notNull(tokenValue ,"登录超时");
            String tempStr= EncryptUtil.aes_decrypt((String)tokenValue);
            Integer id = Integer.valueOf(tempStr.split("-")[0]);
            UserDO param =new UserDO();
            param.setId(id);
            user = userGenerateUtil.generateOne(userDao.selectById(param),UserDTO.class);
        }catch (IllegalArgumentException e){
            Assert.isTrue(false,e.getMessage());
        }catch (Exception e){
            Assert.isTrue(false,"非法token");
        }
        return user;
    }
}
