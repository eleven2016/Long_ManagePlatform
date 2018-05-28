package com.eleven.manage.platform.ModelUtils;

import com.eleven.manage.platform.dto.MenuDTO;
import com.eleven.manage.platform.dto.MenusDTO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单工具类
 * @author ywl
 * @date 2018/5/28
 **/
public class MenuUtil {

    public static List<MenusDTO> generateMenus(List<MenuDTO> allMenus){
        List<MenusDTO> menusDTOList =new ArrayList<>();
        if(!CollectionUtils.isEmpty(allMenus)){
            menusDTOList= allMenus.stream().filter(t->t.getPid() == 0).map(t->{
                MenusDTO menusDTO = transferMenuDto(t);
                menusDTO.setSubMenus(allMenus.stream().filter(menu->t.getId().compareTo(menu.getPid())==0 ).map(item->transferMenuDto(item)).collect(Collectors.toList()));
                return menusDTO;
            }).collect(Collectors.toList());
        }
        return menusDTOList;
    }

    public static MenusDTO transferMenuDto(MenuDTO menuDTO){
        MenusDTO menusDTO=new MenusDTO();
        if(null != menuDTO){
            menusDTO.setId(menuDTO.getId());
            menusDTO.setLevel(menuDTO.getLevel());
            menusDTO.setMenuName(menuDTO.getMenuName());
            menusDTO.setPid(menuDTO.getPid());
            menusDTO.setMenuUrl(menuDTO.getMenuUrl());
        }
        return menusDTO;
    }
}
