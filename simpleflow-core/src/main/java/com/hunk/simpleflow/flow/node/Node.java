package com.hunk.simpleflow.flow.node;

import cn.hutool.core.util.StrUtil;

import com.hunk.simpleflow.flow.BaseNode;
import com.hunk.simpleflow.spring.SpringAware;

import lombok.Getter;
import lombok.ToString;

/**
 * Created on 2023/6/2.
 *
 * @author YCKJ4297
 *     <p>节点基础对象
 */
@Getter
@ToString
public class Node {

    /** bean对象 */
    private BaseNode beanId;
    /** 节点名称 */
    private String beanName;
    /** 执行前事件 */
    private String preEvent;
    /** 执行后事件 */
    private String afterEvent;
    /** 节点描述 */
    private String nodeDesc;

    public void setBeanName(String beanName) {
        this.beanName = beanName;
        this.beanId = SpringAware.getBean(beanName);
    }

    public void setPreEvent(String preEvent) {
        this.preEvent = preEvent;
    }

    public void setAfterEvent(String afterEvent) {
        this.afterEvent = afterEvent;
    }

    public void setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc;
    }

    public boolean isExecutePreEvent() {
        return StrUtil.isNotBlank(preEvent);
    }

    public boolean isExecuteAfterEvent() {
        return StrUtil.isNotBlank(afterEvent);
    }
}
