package com.eleven.manage.platform.quartz.service.impl;

import com.alibaba.fastjson.JSON;
import com.eleven.manage.platform.dto.common.PageResponseDTO;
import com.eleven.manage.platform.dto.common.QuartzDTO;
import com.eleven.manage.platform.mybatis.dao.QuartzDAO;
import com.eleven.manage.platform.mybatis.model.QuartzDO;
import com.eleven.manage.platform.quartz.BaseQuartzJob;
import com.eleven.manage.platform.quartz.service.QuartzUtilService;
import com.eleven.manage.platform.utils.GenerateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * quartz工具服务
 * @author ywl
 * @date 2018/6/1
 **/
@Service("quartzUtilService")
public class QuartzUtilServiceImpl implements QuartzUtilService {
    private static Logger logger = LoggerFactory.getLogger(QuartzUtilServiceImpl.class);

    GenerateUtil<QuartzDO,QuartzDTO>  generateUtil = new GenerateUtil<>();

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private QuartzDAO quartzDAO;

    @Override
    public boolean addQuartzJob(QuartzDTO quartzDTO) {
        boolean result = false;
        try{
            Assert.notNull(quartzDTO,"发起定时任务参数不能为空!");
            Assert.hasText(quartzDTO.getJobClassName(),"发起定时任务[类名称]不能为空!");
            Assert.hasText(quartzDTO.getJobGroupName(),"发起定时任务[组名称]不能为空!");
            Assert.hasText(quartzDTO.getCron(),"发起定时任务[Cron表达式]不能为空!");
            scheduler.start();

            Class<?> class1 = Class.forName(quartzDTO.getJobClassName());

            JobDetail jobDetail = JobBuilder.newJob(((BaseQuartzJob)class1.newInstance()).getClass()).withIdentity(quartzDTO.getJobClassName(),quartzDTO.getJobGroupName()).build();

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzDTO.getCron());

            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(quartzDTO.getJobClassName(),quartzDTO.getJobGroupName()).withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail,trigger);
            result =true;
        }catch (SchedulerException e){
            logger.error("创建定时任务失败"+ JSON.toJSONString(quartzDTO), e);
            result = false;
        }catch(ClassNotFoundException e){
            logger.error("创建定时任务失败"+ JSON.toJSONString(quartzDTO), e);
            result = false;
        }catch(IllegalAccessException e){
            logger.error("创建定时任务失败"+ JSON.toJSONString(quartzDTO), e);
            result = false;
        }catch(InstantiationException e){
            logger.error("创建定时任务失败"+ JSON.toJSONString(quartzDTO), e);
            result = false;
        }
        return result;
    }

    @Override
    public boolean rescheduleJob(QuartzDTO quartzDTO) {
        boolean result = false;
        try{
            Assert.notNull(quartzDTO,"发起定时任务参数不能为空!");
            Assert.hasText(quartzDTO.getJobClassName(),"发起定时任务[类名称]不能为空!");
            Assert.hasText(quartzDTO.getJobGroupName(),"发起定时任务[组名称]不能为空!");
            Assert.hasText(quartzDTO.getCron(),"发起定时任务[Cron表达式]不能为空!");
            TriggerKey triggerKey = TriggerKey.triggerKey(quartzDTO.getJobClassName(), quartzDTO.getJobGroupName());
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzDTO.getCron());

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
            result =true;
        }catch (SchedulerException e){
            logger.error("更新定时任务失败"+ JSON.toJSONString(quartzDTO), e);
            result = false;
        }
        return result;
    }

    @Override
    public boolean pauseQuartzJob(QuartzDTO quartzDTO) {
        boolean result =false;
        try{
            Assert.notNull(quartzDTO,"发起定时任务参数不能为空!");
            Assert.hasText(quartzDTO.getJobClassName(),"发起定时任务[类名称]不能为空!");
            Assert.hasText(quartzDTO.getJobGroupName(),"发起定时任务[组名称]不能为空!");
            scheduler.pauseJob(JobKey.jobKey(quartzDTO.getJobClassName(), quartzDTO.getJobGroupName()));
            result = true;
        }catch (SchedulerException e){
            logger.error("暂停定时任务失败:"+ JSON.toJSONString(quartzDTO), e);
            result = false;
        }

        return result;
    }

    @Override
    public boolean resumeQuartzJob(QuartzDTO quartzDTO) {
        boolean result =false;
        try{
            Assert.notNull(quartzDTO,"发起定时任务参数不能为空!");
            Assert.hasText(quartzDTO.getJobClassName(),"发起定时任务[类名称]不能为空!");
            Assert.hasText(quartzDTO.getJobGroupName(),"发起定时任务[组名称]不能为空!");
            scheduler.resumeJob(JobKey.jobKey(quartzDTO.getJobClassName(), quartzDTO.getJobGroupName()));
            result = true;
        }catch (SchedulerException e){
            logger.error("暂停定时任务失败:"+ JSON.toJSONString(quartzDTO), e);
            result = false;
        }
        return result;
    }

    @Override
    public boolean deleteQuartzJob(QuartzDTO quartzDTO) {
        boolean result =false;
        try{
            Assert.notNull(quartzDTO,"发起定时任务参数不能为空!");
            Assert.hasText(quartzDTO.getJobClassName(),"发起定时任务[类名称]不能为空!");
            Assert.hasText(quartzDTO.getJobGroupName(),"发起定时任务[组名称]不能为空!");
            scheduler.pauseTrigger(TriggerKey.triggerKey(quartzDTO.getJobClassName(), quartzDTO.getJobGroupName()));
            scheduler.unscheduleJob(TriggerKey.triggerKey(quartzDTO.getJobClassName(), quartzDTO.getJobGroupName()));
            scheduler.deleteJob(JobKey.jobKey(quartzDTO.getJobClassName(), quartzDTO.getJobGroupName()));
            result = true;
        }catch (SchedulerException e){
            logger.error("删除定时任务失败:"+ JSON.toJSONString(quartzDTO), e);
            result = false;
        }
        return result;
    }

    @Override
    public PageResponseDTO findQuartzJobs(QuartzDTO quartzDTO, int pageNum, int pageSize) {
        Page<QuartzDO> doPage = PageHelper.startPage(pageNum, pageSize);
        quartzDAO.getJobAndTriggerDetails(quartzDTO.getJobName());
        PageResponseDTO result = generateUtil.generatePage(doPage, QuartzDTO.class);
        logger.info("分页查询结果" + JSON.toJSONString(result));
        return result;
    }
}
