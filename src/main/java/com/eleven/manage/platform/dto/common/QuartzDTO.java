package com.eleven.manage.platform.dto.common;

/**
 * quartz DTO
 * @author ywl
 * @date 2018/6/1
 **/
public class QuartzDTO {
    /**
     * job的类名
     */
    private String jobClassName;
    /**
     * job的组名
     */
    private String jobGroupName;
    /**
     * cron表达式
     */
    private String cron;

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public void setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
