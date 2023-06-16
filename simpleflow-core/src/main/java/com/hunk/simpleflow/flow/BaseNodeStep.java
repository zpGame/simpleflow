package com.hunk.simpleflow.flow;

import com.hunk.simpleflow.consts.Constant;
import com.hunk.simpleflow.error.FlowRuntimeException;
import com.hunk.simpleflow.flow.node.Node;
import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2023/6/12.
 *
 * @author norbit
 *     <p>
 */
@Slf4j
public abstract class BaseNodeStep<T extends Node> {

    private final T node;

    private final String serviceName;

    public BaseNodeStep(T node) {
        this.node = node;
        this.serviceName = node.getBeanName();
    }

    public String getServiceName() {
        return serviceName;
    }

    public String run(IContext ctx) {
        PrintLog.printLog(ctx, this.serviceName);
        BaseNode aresService = node.getBeanId();
        if (aresService == null) {
            return Constant.EXIT;
        }
        // 执行服务
        try {
            aresService.execute(node);
        } catch (Exception e) {
            log.error("执行节点异常", e);
            throw new FlowRuntimeException("Flow execution exception !", e);
        }
        return doCondition(ctx);
    }

    protected abstract String doCondition(IContext ctx);
}
