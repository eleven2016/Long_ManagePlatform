package com.eleven.manage.platform.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eleven.manage.platform.dto.UserDTO;
import com.eleven.manage.platform.service.UserService;
import com.github.pagehelper.PageHelper;

/**
 * user controller
 * @author ywl
 * @date 2018/5/16
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(@RequestBody UserDTO user){
        return userService.addUser(user);
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(@RequestParam(name = "pageNum", required = false, defaultValue = "1")int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        //开始分页
        PageHelper.startPage(pageNum,pageSize);
        return userService.findByPage(null,pageNum,pageSize);
    }
}
