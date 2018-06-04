package com.eleven.manage.platform.web;

import com.eleven.manage.platform.dto.common.PageResponseDTO;
import com.eleven.manage.platform.dto.common.QuartzDTO;
import com.eleven.manage.platform.dto.common.ResponseDTO;
import com.eleven.manage.platform.quartz.service.QuartzUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * quartz 控制器
 * @author ywl
 * @date 2018/6/4
 **/
@Controller
@RequestMapping("/quartz")
public class QuartzController {

    private static Logger logger = LoggerFactory.getLogger(QuartzController.class);

    @Autowired
    private QuartzUtilService quartzUtilService;

    /**
     * 添加job
     * @param quartzDTO
     * @return
     */
    @PostMapping("/addJob")
    @ResponseBody
    public ResponseDTO addJob(@RequestBody QuartzDTO quartzDTO){
        ResponseDTO result =new ResponseDTO();
        try{
            boolean jobResult = quartzUtilService.addQuartzJob(quartzDTO);
            result.setSuccess(jobResult);
        }catch(IllegalArgumentException e){
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        }catch(Exception e){
            logger.error("QuartzController添加定时任务",e);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 更新job
     * @param quartzDTO
     * @return
     */
    @PostMapping("/updateJob")
    @ResponseBody
    public ResponseDTO updateJob(@RequestBody QuartzDTO quartzDTO){
        ResponseDTO result =new ResponseDTO();
        try{
            boolean jobResult = quartzUtilService.rescheduleJob(quartzDTO);
            result.setSuccess(jobResult);
        }catch(IllegalArgumentException e){
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        }catch(Exception e){
            logger.error("QuartzController更新定时任务",e);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 暂停job
     * @param quartzDTO
     * @return
     */
    @PostMapping("/pauseJob")
    @ResponseBody
    public ResponseDTO pauseJob(@RequestBody QuartzDTO quartzDTO){
        ResponseDTO result =new ResponseDTO();
        try{
            boolean jobResult = quartzUtilService.pauseQuartzJob(quartzDTO);
            result.setSuccess(jobResult);
        }catch(IllegalArgumentException e){
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        }catch(Exception e){
            logger.error("QuartzController暂停定时任务",e);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 恢复job
     * @param quartzDTO
     * @return
     */
    @PostMapping("/resumeJob")
    @ResponseBody
    public ResponseDTO resumeJob(@RequestBody QuartzDTO quartzDTO){
        ResponseDTO result =new ResponseDTO();
        try{
            boolean jobResult = quartzUtilService.resumeQuartzJob(quartzDTO);
            result.setSuccess(jobResult);
        }catch(IllegalArgumentException e){
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        }catch(Exception e){
            logger.error("QuartzController恢复定时任务",e);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 恢复job
     * @param quartzDTO
     * @return
     */
    @PostMapping("/deleteJob")
    @ResponseBody
    public ResponseDTO deleteJob(@RequestBody QuartzDTO quartzDTO){
        ResponseDTO result =new ResponseDTO();
        try{
            boolean jobResult = quartzUtilService.deleteQuartzJob(quartzDTO);
            result.setSuccess(jobResult);
        }catch(IllegalArgumentException e){
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        }catch(Exception e){
            logger.error("QuartzController删除定时任务",e);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 查询job
     * @param quartzDTO
     * @return
     */
    @PostMapping("/queryJobs")
    @ResponseBody
    public PageResponseDTO queryJobs(@RequestBody(required = false) QuartzDTO quartzDTO, @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                     @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        PageResponseDTO result =new PageResponseDTO();
        try{
            if(null == quartzDTO){
                quartzDTO =new QuartzDTO();
            }
            PageResponseDTO jobResult = quartzUtilService.findQuartzJobs(quartzDTO,pageNum, pageSize);
            result.setSuccess(true);
            result.setData(jobResult);
        }catch(IllegalArgumentException e){
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        }catch(Exception e){
            logger.error("QuartzController查询定时任务",e);
            result.setSuccess(false);
        }
        return result;
    }
}
