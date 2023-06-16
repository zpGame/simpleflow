package com.hunk.simpleflow.flow;

import com.hunk.simpleflow.event.NodeEvent;
import com.hunk.simpleflow.flow.node.Node;
import com.hunk.simpleflow.spring.SpringAware;
import com.hunk.simpleflow.utils.ThreadLocalUtil;

/**
 * Created on 2023/4/24.
 *
 * @author norbit
 *     <p>可编排原子服务
 */
public abstract class BaseNode {

    protected <T> T getBeanByClass(Class<T> aClass) {
        return ThreadLocalUtil.get(aClass);
    }

    protected void setConditionParam(String param, String value) {
        ThreadLocalUtil.get(ConditionContext.class).setParam(param, value);
    }

    public void execute(Node node) {
        if (node.isExecutePreEvent()) beforeEvent();
        process();
        if (node.isExecuteAfterEvent()) afterEvent();
    }

    public void beforeEvent() {
        SpringAware.getBean(NodeEvent.class).preEvent();
    }

    public void afterEvent() {
        SpringAware.getBean(NodeEvent.class).afterEvent();
    }

    /** 子类实现类，用于业务 */
    protected abstract void process();
}
