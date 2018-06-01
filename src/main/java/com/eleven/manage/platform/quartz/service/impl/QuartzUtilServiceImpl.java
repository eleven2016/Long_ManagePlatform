package com.eleven.manage.platform.quartz.service.impl;

import com.eleven.manage.platform.dto.common.QuartzDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eleven.manage.platform.quartz.service.QuartzUtilService;

import java.util.List;

/**
 * quartz工具服务
 * @author ywl
 * @date 2018/6/1
 **/
@Service("quartzUtilService")
public class QuartzUtilServiceImpl implements QuartzUtilService {
    private static Logger logger = LoggerFactory.getLogger(QuartzUtilServiceImpl.class);


    @Override
    public boolean addQuartzJob(QuartzDTO quartzDTO) {
        return false;
    }

    @Override
    public boolean pauseQuartzJob(QuartzDTO quartzDTO) {
        return false;
    }

    @Override
    public boolean resumeQuartzJob(QuartzDTO quartzDTO) {
        return false;
    }

    @Override
    public boolean deleteQuartzJob(QuartzDTO quartzDTO) {
        return false;
    }

    @Override
    public List<QuartzDTO> findQuartzJobs(QuartzDTO quartzDTO, int pageNum, int pageSize) {
        return null;
    }
}
