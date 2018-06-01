package com.eleven.manage.platform.quartz.jobs;

import com.eleven.manage.platform.quartz.BaseQuartzJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ywl
 * @date 2018/6/1
 **/
public class TestJob implements BaseQuartzJob {

    private static Logger logger = LoggerFactory.getLogger(TestJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("执行测试job");
    }

    public TestJob() {
        logger.info("创建quartz测试job");
    }
}
