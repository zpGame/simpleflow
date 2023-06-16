package com.hunk.simpleflow.flow;

import cn.hutool.core.collection.CollUtil;

import com.hunk.simpleflow.consts.Constant;
import com.hunk.simpleflow.flow.node.FlowWhen;
import com.hunk.simpleflow.flow.node.StartNode;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created on 2023/6/7.
 *
 * @author norbit
 *     <p>
 */
@Slf4j
public class StartNodeStep extends BaseNodeStep<StartNode> {

    private final StartNode startNode;

    public static StartNodeStep of(StartNode startNode) {
        return new StartNodeStep(startNode);
    }

    public StartNodeStep(StartNode startNode) {
        super(startNode);
        this.startNode = startNode;
    }

    @Override
    protected String doCondition(IContext ctx) {
        List<FlowWhen> flowWhens = CollUtil.emptyIfNull(startNode.getFlowWhens());
        log.debug("condition flowWhens:{}", flowWhens);
        // 是否为条件判断，识别下一节点；
        return flowWhens.stream()
                .filter(when -> when.getCondition().check(ctx))
                .map(FlowWhen::getTo)
                .findFirst()
                .orElse(Constant.EXIT);
    }
}
