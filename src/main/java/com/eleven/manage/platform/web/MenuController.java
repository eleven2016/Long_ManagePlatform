package com.eleven.manage.platform.web;

import com.alibaba.fastjson.JSON;
import com.eleven.manage.platform.ModelUtils.MenuUtil;
import com.eleven.manage.platform.dto.MenuDTO;
import com.eleven.manage.platform.dto.PageResponseDTO;
import com.eleven.manage.platform.dto.ResponseDTO;
import com.eleven.manage.platform.dto.UserDTO;
import com.eleven.manage.platform.service.LoginService;
import com.eleven.manage.platform.service.MenuService;
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
 * menu控制器
 * @author ywl
 * @date 2018/5/25
 **/
@Controller
@RequestMapping(value = "/menu")
public class MenuController {

    private static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService   menuService;

    @Autowired
    private LoginService  loginService;

    /**
     * 添加菜单
     * @param menuDTO
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public ResponseDTO addMenu(@RequestBody MenuDTO menuDTO) {
        logger.info("添加菜单参数:" + JSON.toJSONString(menuDTO));
        ResponseDTO result = new ResponseDTO();

        try {
            Assert.isTrue(null != menuDTO, "参数不能为空!");
            Assert.hasText(menuDTO.getMenuName(), "参数菜单名称不能为空!");
            if (null != menuDTO.getId() && 0 != menuDTO.getId()) {
                menuService.update(menuDTO);
            } else {
                Assert.isTrue(!this.isDuplicate(menuDTO.getMenuName()), "该菜单已经存在!");
                menuService.insert(menuDTO);
            }
            result.setSuccess(true);
        } catch (IllegalArgumentException e) {
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        } catch (Exception e) {
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public ResponseDTO deleteMenu(@RequestParam(name = "id") int id) {
        ResponseDTO result = new ResponseDTO();
        try {
            menuService.deleteById(id);
            result.setSuccess(true);
        } catch (IllegalArgumentException e) {
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        } catch (Exception e) {
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 分页查询菜单
     * @param menuDTO
     * @return
     */
    @PostMapping("/queryByPage")
    @ResponseBody
    public PageResponseDTO queryByPage(@RequestBody(required = false) MenuDTO menuDTO, @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        PageResponseDTO result = new PageResponseDTO();
        try {
            result = menuService.selectByPage(menuDTO, pageNum, pageSize);
        } catch (IllegalArgumentException e) {
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        } catch (Exception e) {
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 组织菜单
     * @return
     */
    @PostMapping("/generateMenus")
    @ResponseBody
    public ResponseDTO generateMenus(@RequestParam(name = "token") String token) {
        ResponseDTO result = new ResponseDTO();
        try {
            UserDTO userDTO = loginService.getUserByToken(token);
            List<MenuDTO> queryResult = menuService.selectByUser(userDTO.getId());
            result.setData(MenuUtil.generateMenus(queryResult));
            result.setSuccess(true);
        } catch (IllegalArgumentException e) {
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        } catch (Exception e) {
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
    public ResponseDTO queryByCondition(@RequestBody(required = false) MenuDTO menuDTO) {
        ResponseDTO result = new ResponseDTO();
        try {
            List<MenuDTO> queryResult = menuService.selectByCondition(menuDTO);
            result.setData(queryResult);
            result.setSuccess(true);
        } catch (IllegalArgumentException e) {
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("系统错误", e);
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
    public ResponseDTO queryById(@RequestParam(name = "id") int id) {
        ResponseDTO result = new ResponseDTO();
        try {
            MenuDTO param = new MenuDTO();
            param.setId(id);
            MenuDTO menuDTO = menuService.selectById(param);
            result.setSuccess(true);
            result.setData(menuDTO);
        } catch (Exception e) {
            logger.error("系统错误", e);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 校验重复性
     * @param menuName
     * @return
     */
    private boolean isDuplicate(String menuName) {
        MenuDTO param = new MenuDTO();
        param.setMenuName(menuName);
        List<MenuDTO> menuDTOS = menuService.selectByCondition(param);
        if (CollectionUtils.isEmpty(menuDTOS)) {
            return false;
        } else {
            Optional<MenuDTO> duplicate = menuDTOS.stream().filter(t -> menuName.equals(t.getMenuName())).findFirst();
            if (duplicate.isPresent()) {
                return true;
            } else {
                return false;
            }
        }
    }
}
