package com.eleven.manage.platform.web;

import com.alibaba.fastjson.JSON;
import com.eleven.manage.platform.ModelUtils.RoleUtil;
import com.eleven.manage.platform.dto.common.PageResponseDTO;
import com.eleven.manage.platform.dto.common.ResponseDTO;
import com.eleven.manage.platform.dto.basic.RoleDTO;
import com.eleven.manage.platform.dto.basic.RolePermissionMapperDTO;
import com.eleven.manage.platform.service.RoleService;
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
 * 角色控制器
 * @author ywl
 * @date 2018/5/30
 **/
@Controller
@RequestMapping(value = "/role")
public class RoleController {
    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    /**
     * 添加角色
     * @param roleDTO
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public ResponseDTO add(@RequestBody RoleDTO roleDTO){
        logger.info("添加角色参数:"+ JSON.toJSONString(roleDTO));
        ResponseDTO result =new ResponseDTO();
        try{
            Assert.isTrue(null != roleDTO,"参数不能为空!");
            Assert.hasText( roleDTO.getRoleName(),"参数角色名称不能为空!");
            if(null != roleDTO.getId() && 0 != roleDTO.getId()){
                roleService.update(roleDTO);
            }else{
                Assert.isTrue(!this.isDuplicate(roleDTO.getRoleName()),"该角色已经存在!");
                roleService.insert(roleDTO);
            }
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
     * 判断重复权限
     * @param name
     * @return
     */
    private boolean isDuplicate(String name){
        RoleDTO  param=new RoleDTO();
        param.setRoleName(name);
        List<RoleDTO> roleDTOS = roleService.selectByCondition(param);
        if(CollectionUtils.isEmpty(roleDTOS)){
            return false;
        }else{
            Optional<RoleDTO> duplicate =roleDTOS.stream().filter(t->name.equals(t.getRoleName())).findFirst();
            if(duplicate.isPresent()){
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @PostMapping("/queryById")
    @ResponseBody
    public ResponseDTO queryById(@RequestParam(name = "id")int id){
        ResponseDTO result =new ResponseDTO();
        try{
            RoleDTO param=new RoleDTO();
            param.setId(id);
            RoleDTO roleDTO =roleService.selectById(param);
            result.setSuccess(true);
            result.setData(roleDTO);
        }catch(Exception e){
            logger.error("系统错误",e);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 分页查询
     * @param roleDTO
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/queryByPage")
    @ResponseBody
    public PageResponseDTO queryByPage(@RequestBody(required = false) RoleDTO roleDTO, @RequestParam(name = "pageNum", required = false, defaultValue = "1")int pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        PageResponseDTO result =new PageResponseDTO();
        try{
            result = roleService.selectByPage(roleDTO,pageNum,pageSize);
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
     * 根据角色查询权限
     * @param id
     * @return
     */
    @PostMapping("/findPermissionsByRoleId")
    @ResponseBody
    public ResponseDTO findPermissionsByRoleId(@RequestParam(name = "id")int id){
        ResponseDTO result =new ResponseDTO();
        try{
            Assert.isTrue(id>0,"id不合法!");
            List<RolePermissionMapperDTO> rolePermissionMapperDTOS = roleService.findPermissionsByRoleId(id);
            result.setData(RoleUtil.generateRolePer(rolePermissionMapperDTOS));
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
    public ResponseDTO queryByCondition(@RequestBody(required = false) RoleDTO roleDTO){
        ResponseDTO result =new ResponseDTO();
        try{
            List<RoleDTO> queryResult = roleService.selectByCondition(roleDTO);
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
    public ResponseDTO saveRolePerMap(@RequestBody RolePermissionMapperDTO param){
        ResponseDTO result =new ResponseDTO();
        try{
            Assert.notNull(param,"参数不能为空!");
            roleService.saveRolePerMap(param);
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
