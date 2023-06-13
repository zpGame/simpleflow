package com.hunk.simpleflow.flow;

import cn.hutool.core.collection.CollUtil;

import com.hunk.simpleflow.consts.Constant;
import com.hunk.simpleflow.flow.node.FlowNode;
import com.hunk.simpleflow.flow.node.FlowWhen;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created on 2023/4/27.
 *
 * @author YCKJ4297
 *     <p>流程服务节点
 */
@Slf4j
public class FlowNodeStep extends BaseNodeStep<FlowNode> {

    private final FlowNode flowNode;

    public static FlowNodeStep of(FlowNode flowNode) {
        return new FlowNodeStep(flowNode);
    }

    public FlowNodeStep(FlowNode flowNode) {
        super(flowNode);
        this.flowNode = flowNode;
    }

    @Override
    protected String doCondition(IContext ctx) {
        // 设置默认的跳转：EXIT
        List<FlowWhen> flowWhens = CollUtil.emptyIfNull(flowNode.getFlowWhens());
        log.debug("condition flowWhens:{}", flowWhens);
        // 是否为条件判断，识别下一节点；
        return flowWhens.stream()
                .filter(when -> when.getCondition().check(ctx))
                .map(FlowWhen::getTo)
                .findFirst()
                .orElse(Constant.EXIT);
    }
}
