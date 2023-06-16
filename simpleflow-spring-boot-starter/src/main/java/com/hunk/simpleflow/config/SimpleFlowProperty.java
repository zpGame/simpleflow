package com.hunk.simpleflow.config;

import com.hunk.simpleflow.enums.FileTypeEnum;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created on 2023/4/22.
 *
 * @author norbit
 *     <p>
 */
@ConfigurationProperties(prefix = "simple.flow")
public class SimpleFlowProperty {

    /** 流程定义资源地址 */
    private String ruleSource;

    /** 是否启动初始化 */
    private boolean startInit;

    private FileTypeEnum fileType;

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
        return null == fileType ? FileTypeEnum.XML : fileType;
    }

    public void setFileType(FileTypeEnum fileType) {
        this.fileType = fileType;
    }
}
