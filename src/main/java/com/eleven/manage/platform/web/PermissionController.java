package com.eleven.manage.platform.web;

import com.alibaba.fastjson.JSON;
import com.eleven.manage.platform.ModelUtils.PermissionUtil;
import com.eleven.manage.platform.dto.PageResponseDTO;
import com.eleven.manage.platform.dto.PermissionDTO;
import com.eleven.manage.platform.dto.PermissionMenuMapperDTO;
import com.eleven.manage.platform.dto.ResponseDTO;
import com.eleven.manage.platform.service.PermissionService;
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
 * 权限控制器
 * @author ywl
 * @date 2018/5/30
 **/
@Controller
@RequestMapping(value = "/permission")
public class PermissionController {
    private static Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private PermissionService permissionService;

    /**
     * 编辑权限
     * @param permissionDTO
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public ResponseDTO addPermission(@RequestBody PermissionDTO permissionDTO){
        logger.info("添加权限参数:"+ JSON.toJSONString(permissionDTO));
        ResponseDTO result =new ResponseDTO();
        try{
            Assert.isTrue(null != permissionDTO,"参数不能为空!");
            Assert.hasText( permissionDTO.getName(),"参数权限名称不能为空!");
            if(null != permissionDTO.getId() && 0 != permissionDTO.getId()){
                permissionService.update(permissionDTO);
            }else{
                Assert.isTrue(!this.isDuplicate(permissionDTO.getName()),"该权限已经存在!");
                permissionService.insert(permissionDTO);
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
        PermissionDTO param=new PermissionDTO();
        param.setName(name);
        List<PermissionDTO> permissionDTOS = permissionService.selectByCondition(param);
        if(CollectionUtils.isEmpty(permissionDTOS)){
            return false;
        }else{
            Optional<PermissionDTO> duplicate =permissionDTOS.stream().filter(t->name.equals(t.getName())).findFirst();
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
            PermissionDTO param=new PermissionDTO();
            param.setId(id);
            PermissionDTO permissionDTO =permissionService.selectById(param);
            result.setSuccess(true);
            result.setData(permissionDTO);
        }catch(Exception e){
            logger.error("系统错误",e);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 分页查询
     * @param permissionDTO
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/queryByPage")
    @ResponseBody
    public PageResponseDTO queryByPage(@RequestBody(required = false) PermissionDTO permissionDTO, @RequestParam(name = "pageNum", required = false, defaultValue = "1")int pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        PageResponseDTO result =new PageResponseDTO();
        try{
            result = permissionService.selectByPage(permissionDTO,pageNum,pageSize);
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
     *  根据权限ID查询关联的菜单
     * @param id
     * @return
     */
    @PostMapping("/findMenusByPerId")
    @ResponseBody
    public ResponseDTO findMenusByPermission(@RequestParam(name = "id")int id){
        ResponseDTO result =new ResponseDTO();
        try{
            Assert.isTrue(id>0,"id不合法!");
            List<PermissionMenuMapperDTO> permissionMenuMapperDTOS = permissionService.findMenusByPermission(id);
            result.setData(PermissionUtil.generatePerMenu(permissionMenuMapperDTOS));
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
     * 保存权限与菜单的关联
     * @param param
     * @return
     */
    @PostMapping("/savePerMenuMap")
    @ResponseBody
    public ResponseDTO savePerMenuMap(@RequestBody PermissionMenuMapperDTO param){
        PageResponseDTO result =new PageResponseDTO();
        try{
            Assert.notNull(param,"参数不能为空!");
            permissionService.savePerMenuMap(param);
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
    public ResponseDTO queryByCondition(@RequestBody(required = false) PermissionDTO permissionDTO){
        ResponseDTO result =new ResponseDTO();
        try{
            List<PermissionDTO> queryResult = permissionService.selectByCondition(permissionDTO);
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
}
