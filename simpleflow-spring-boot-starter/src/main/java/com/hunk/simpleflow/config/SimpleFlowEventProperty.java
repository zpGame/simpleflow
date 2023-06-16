package com.hunk.simpleflow.config;

import cn.hutool.core.util.StrUtil;
import com.hunk.simpleflow.enums.EventEnum;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Optional;

/**
 * Created on 2023/6/16.
 *
 * @author norbit
 *     <p>
 */
@ConfigurationProperties(prefix = "simple.flow.event")
public class SimpleFlowEventProperty {

    private EventEnum eventEnum;

    /** 异步线程名称 */
    private String nameFormat = "asyncEventBus";

    /** 核心池大小 */
    private int corePoolSize = 6;

    /** 最大池大小 */
    private int maximumPoolSize = 10;

    /** 存活时间，单位秒 */
    private long keepAliveTime = 60L;

    /** 容量 */
    private int capacity = 2048;

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
