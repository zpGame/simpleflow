package com.hunk.simpleflow.property;

import com.hunk.simpleflow.enums.EventEnum;
import com.hunk.simpleflow.enums.FileTypeEnum;

/**
 * Created on 2023/4/24.
 *
 * @author norbit
 *     <p>
 */
public class SimpleFlowConfig {

    /** 流程定义资源地址 */
    private String ruleSource;

    /** 是否启动初始化 */
    private boolean startInit;

    private FileTypeEnum fileType;

    private EventEnum eventEnum;

    /** 异步线程名称 */
    private String nameFormat;

    /** 核心池大小 */
    private int corePoolSize;

    /** 最大池大小 */
    private int maximumPoolSize;

    /** 存活时间，单位秒 */
    private long keepAliveTime;

    /** 容量 */
    private int capacity;

    public String getRuleSource() {
        return ruleSource;
    }

    public void setRuleSource(String ruleSource) {
        this.ruleSource = ruleSource;
    }

    public boolean isStartInit() {
        return startInit;
    }

    public void setStartInit(boolean startInit) {
        this.startInit = startInit;
    }

    public FileTypeEnum getFileType() {
        return fileType;
    }

    public String getFileTypeToStr() {
        return fileType.getSuffixName();
    }

    public void setFileType(FileTypeEnum fileType) {
        this.fileType = fileType;
    }

    public EventEnum getEventEnum() {
        return eventEnum;
    }

    public void setEventEnum(EventEnum eventEnum) {
        this.eventEnum = eventEnum;
    }

    public String getNameFormat() {
        return nameFormat;
    }

    public void setNameFormat(String nameFormat) {
        this.nameFormat = nameFormat;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
