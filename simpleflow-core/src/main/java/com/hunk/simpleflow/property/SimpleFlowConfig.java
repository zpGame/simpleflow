package com.hunk.simpleflow.property;

import com.hunk.simpleflow.enums.FileTypeEnum;

/**
 * Created on 2023/4/24.
 *
 * @author YCKJ4297
 *     <p>
 */
public class SimpleFlowConfig {

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
        return fileType;
    }

    public String getFileTypeToStr() {
        return fileType.getSuffixName();
    }

    public void setFileType(FileTypeEnum fileType) {
        this.fileType = fileType;
    }
}
