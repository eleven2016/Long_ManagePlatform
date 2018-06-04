package com.eleven.manage.platform.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 基础Job接口
 * @author ywl
 * @date 2018/6/1
 **/
public interface BaseQuartzJob extends Job {
    @Override
    void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException;
}
