package com.eleven.manage.platform.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ywl
 * @date 2018/5/16
 **/
@Controller
public class IndexController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        logger.info("测试日志集成");
        return "test/test";
    }

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    @ResponseBody
    public String jsonTest(){
        return "{id:1}";
    }
}
