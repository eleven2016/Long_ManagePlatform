package com.eleven.manage.platform.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.eleven.manage.platform.dto.common.RedisDTO;
import com.eleven.manage.platform.dto.common.ResponseDTO;
import com.eleven.manage.platform.redis.RedisService;

/**
 * redis控制器
 * @author ywl
 * @date 2018/6/1
 **/
@Controller
@RequestMapping("/redis")
public class RedisController {

    private static Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisService  redisService;

    /**
     * 添加缓存
     * @param redisDTO
     * @return
     */
    @PostMapping("/setValue")
    @ResponseBody
    public ResponseDTO setValue(@RequestBody RedisDTO redisDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            logger.info("加入缓存->" + JSON.toJSONString(redisDTO));
            Assert.notNull(redisDTO, "参数不能为空");
            Assert.hasText(redisDTO.getKey(), "key不能为空");
            Assert.hasText(redisDTO.getValue(), "value不能为空");
            responseDTO.setSuccess(redisService.set(redisDTO.getKey(), redisDTO.getValue()));
        } catch (IllegalArgumentException e) {
            responseDTO.setSuccess(false);
            responseDTO.setErrorMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("加入缓存错误", e);
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }

    /**
     * 添加缓存
     * @param redisDTO
     * @return
     */
    @PostMapping("/setValueWithTime")
    @ResponseBody
    public ResponseDTO setValueWithTime(@RequestBody RedisDTO redisDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            logger.info("加入缓存->" + JSON.toJSONString(redisDTO));
            Assert.notNull(redisDTO, "参数不能为空");
            Assert.hasText(redisDTO.getKey(), "key不能为空");
            Assert.hasText(redisDTO.getValue(), "value不能为空");
            Assert.notNull(redisDTO.getLimitTime(), "limitTime不能为空");
            responseDTO.setSuccess(redisService.set(redisDTO.getKey(), redisDTO.getValue(), redisDTO.getLimitTime()));
        } catch (IllegalArgumentException e) {
            responseDTO.setSuccess(false);
            responseDTO.setErrorMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("加入缓存错误", e);
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }

    @PostMapping("/getValue")
    @ResponseBody
    public ResponseDTO getValue(@RequestBody RedisDTO redisDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            logger.info("从缓存中获取->" + JSON.toJSONString(redisDTO));
            Assert.notNull(redisDTO, "参数不能为空");
            Assert.hasText(redisDTO.getKey(), "key不能为空");
            responseDTO.setSuccess(true);
            responseDTO.setData(redisService.get(redisDTO.getKey()));
        } catch (IllegalArgumentException e) {
            responseDTO.setSuccess(false);
            responseDTO.setErrorMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("加入缓存错误", e);
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }

}
