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
    private String  jobClassName;
    /**
     * cron表达式
     */
    private String  cron;

    private String  jobName;
    private String  jobGroup;
    private String  triggerName;
    private String  triggerGroup;
    private Integer repeatInterval;
    private Integer timesTriggered;

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }


    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public Integer getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(Integer repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public Integer getTimesTriggered() {
        return timesTriggered;
    }

    public void setTimesTriggered(Integer timesTriggered) {
        this.timesTriggered = timesTriggered;
    }
}
