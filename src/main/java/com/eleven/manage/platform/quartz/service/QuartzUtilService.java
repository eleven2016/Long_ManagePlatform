package com.eleven.manage.platform.quartz.service;

import com.eleven.manage.platform.dto.common.PageResponseDTO;
import com.eleven.manage.platform.dto.common.QuartzDTO;

/**
 * quartz工具服务
 * @author ywl
 * @date 2018/6/1
 **/
public interface QuartzUtilService {

    /**
     * 添加job
     * @param quartzDTO
     * @return
     */
    boolean addQuartzJob(QuartzDTO quartzDTO);
    /**
     * 启动ob
     * @param quartzDTO
     * @return
     */
    boolean startQuartzJob(QuartzDTO quartzDTO);

    /**
     * 更新job
     * @param quartzDTO
     * @return
     */
    boolean rescheduleJob(QuartzDTO quartzDTO);

    /**
     * 暂停job
     * @param quartzDTO
     * @return
     */
    boolean pauseQuartzJob(QuartzDTO quartzDTO);

    /**
     * 恢复job
     * @param quartzDTO
     * @return
     */
    boolean resumeQuartzJob(QuartzDTO quartzDTO);

    /**
     * 删除job
     * @param quartzDTO
     * @return
     */
    boolean deleteQuartzJob(QuartzDTO quartzDTO);

    /**
     * 分页查询job
     * @param quartzDTO
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResponseDTO findQuartzJobs(QuartzDTO quartzDTO, int pageNum, int pageSize);
}
