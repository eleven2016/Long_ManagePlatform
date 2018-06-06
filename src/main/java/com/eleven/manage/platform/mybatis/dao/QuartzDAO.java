package com.eleven.manage.platform.mybatis.dao;

import com.eleven.manage.platform.mybatis.model.QuartzDO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ywl
 * @date 2018/6/4
 **/
@Service("quartzDAO")
public interface QuartzDAO {
    List<QuartzDO> getJobAndTriggerDetails(QuartzDO quartzDO);
}
