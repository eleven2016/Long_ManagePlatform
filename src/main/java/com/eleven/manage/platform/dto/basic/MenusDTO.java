package com.eleven.manage.platform.dto.basic;

import java.util.List;

/**
 * @author ywl
 * @date 2018/5/28
 **/
public class MenusDTO {
    private Integer id;

    private String menuName;

    private Integer pid;

    private Integer level;

    private String menuUrl;

    private List<MenusDTO> subMenus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public List<MenusDTO> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<MenusDTO> subMenus) {
        this.subMenus = subMenus;
    }
}
