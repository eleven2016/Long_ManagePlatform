package com.eleven.manage.platform.web;

import com.alibaba.fastjson.JSON;
import com.eleven.manage.platform.ModelUtils.UserUtil;
import com.eleven.manage.platform.dto.PageResponseDTO;
import com.eleven.manage.platform.dto.ResponseDTO;
import com.eleven.manage.platform.dto.UserDTO;
import com.eleven.manage.platform.dto.UserRoleMapperDTO;
import com.eleven.manage.platform.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

/**
 * user controller
 * @author ywl
 * @date 2018/5/16
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     * 添加用户
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/add")
    public ResponseDTO addUser(@RequestBody UserDTO user){
        logger.info("添加用户参数:"+ JSON.toJSONString(user));
        ResponseDTO result =new ResponseDTO();

        try{
            Assert.isTrue(null != user,"参数不能为空!");
            Assert.hasText( user.getUserName(),"参数用户名称不能为空!");
            if(null != user.getId() && 0 != user.getId()){
                userService.updateUser(user);
            }else{
                Assert.isTrue(!this.isDuplicate(user.getUserName()),"该菜单已经存在!");
                userService.addUser(user);
            }
            result.setSuccess(true);
        }catch (IllegalArgumentException e){
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        }catch (Exception e){
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 校验重复性
     * @param name
     * @return
     */
    private boolean isDuplicate(String name){
        UserDTO param=new UserDTO();
        param.setUserName(name);
        List<UserDTO> userDTOS = userService.findByCondition(param);
        if(CollectionUtils.isEmpty(userDTOS)){
            return false;
        }else{
            Optional<UserDTO> duplicate =userDTOS.stream().filter(t->name.equals(t.getUserName())).findFirst();
            if(duplicate.isPresent()){
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public ResponseDTO deleteUser(@RequestParam(name = "id")int id){
        ResponseDTO result =new ResponseDTO();
        try{
            userService.deleteUser(id);
            result.setSuccess(true);
        }catch (IllegalArgumentException e){
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        }catch (Exception e){
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 分页查询菜单
     * @param userDTO
     * @return
     */
    @PostMapping("/queryByPage")
    @ResponseBody
    public PageResponseDTO queryByPage(@RequestBody(required = false) UserDTO userDTO, @RequestParam(name = "pageNum", required = false, defaultValue = "1")int pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        PageResponseDTO result =new PageResponseDTO();
        try{
            result = userService.findByPage(userDTO,pageNum,pageSize);
        }catch (IllegalArgumentException e){
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        }catch (Exception e){
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @PostMapping("/queryById")
    @ResponseBody
    public ResponseDTO queryById(@RequestParam(name = "id")int id){
        ResponseDTO result =new ResponseDTO();
        try{
            UserDTO userDTO =userService.findById(id);
            result.setSuccess(true);
            result.setData(userDTO);
        }catch(Exception e){
            logger.error("系统错误",e);
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 根据用户查询角色
     * @param userId
     * @return
     */
    @PostMapping("/findRolesByUserId")
    @ResponseBody
    public ResponseDTO findRolesByUserId(@RequestParam(name = "id")int userId){
        ResponseDTO result =new ResponseDTO();
        try{
            Assert.isTrue(userId>0,"userId不合法!");
            List<UserRoleMapperDTO> userRoleMapperDTOS = userService.findRolesByUserId(userId);
            result.setData(UserUtil.generateUserRole(userRoleMapperDTOS));
            result.setSuccess(true);
        }catch (IllegalArgumentException e){
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        }catch (Exception e){
            logger.error("系统错误", e);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 根据条件查询
     * @return
     */
    @PostMapping("/queryByCondition")
    @ResponseBody
    public ResponseDTO queryByCondition(@RequestBody(required = false) UserDTO userDTO){
        ResponseDTO result =new ResponseDTO();
        try{
            List<UserDTO> queryResult = userService.findByCondition(userDTO);
            result.setData(queryResult);
            result.setSuccess(true);
        }catch (IllegalArgumentException e){
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        }catch (Exception e){
            logger.error("系统错误",e);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 保存角色与权限的关联
     * @param param
     * @return
     */
    @PostMapping("/saveRolePerMap")
    @ResponseBody
    public ResponseDTO saveRolePerMap(@RequestBody UserRoleMapperDTO param){
        ResponseDTO result =new ResponseDTO();
        try{
            Assert.notNull(param,"参数不能为空!");
            userService.saveUserRoleMap(param);
            result.setSuccess(true);
        }catch (IllegalArgumentException e){
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        }catch (Exception e){
            logger.error("系统错误", e);
            result.setSuccess(false);
        }
        return result;
    }
}
