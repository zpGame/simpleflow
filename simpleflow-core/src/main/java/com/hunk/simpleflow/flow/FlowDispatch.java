package com.hunk.simpleflow.flow;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import com.hunk.simpleflow.base.DispatchData;
import com.hunk.simpleflow.base.FlowDataBus;
import com.hunk.simpleflow.consts.Constant;
import com.hunk.simpleflow.error.NodeNotExistException;
import com.hunk.simpleflow.flow.node.EndNode;
import com.hunk.simpleflow.flow.node.StartNode;
import com.hunk.simpleflow.utils.ThreadLocalUtil;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created on 2023/5/18.
 *
 * @author norbit
 *     <p>
 */
@Slf4j
public class FlowDispatch {

    private String flowStep;

    private final String flowName;

    private final Class<?>[] aClass;

    private final DispatchData dispatchData;

    public static FlowDispatch of(String flowName, Class<?>[] aClass) {
        return new FlowDispatch(flowName, aClass);
    }

    public FlowDispatch(String flowName, Class<?>[] aClass) {
        this.aClass = aClass;
        this.flowName = flowName;
        this.dispatchData = FlowDataBus.getByFlowName(flowName);
    }

    public FlowResponse execute(String nodeStep) {
        StartNode startNode = dispatchData.getStartNode();
        if (ObjectUtil.isEmpty(startNode)) {
            throw new NodeNotExistException("startNode does not exist !!");
        }
        try {
            doEndNode(doFlowNode(doStartNode(nodeStep)));
        } catch (Exception e) {
            return fail(e);
        }
        return success();
    }

    private FlowResponse fail(Exception e) {
        return FlowResponse.fail(
                e,
                NodeStep.builder()
                        .flowName(flowName)
                        .flowStep(flowStep)
                        .params(ThreadLocalUtil.get(ConditionContext.class).getParamMap())
                        .build());
    }

    private FlowResponse success() {
        return ObjectUtil.isEmpty(aClass)
                ? FlowResponse.success()
                : FlowResponse.success(
                        Arrays.stream(aClass)
                                .map(ThreadLocalUtil::get)
                                .collect(Collectors.toList()));
    }

    /**
     * 开始节点
     *
     * @param nodeStep 预设节点名称
     * @return nextNode
     */
    private String doStartNode(String nodeStep) {
        StartNode startNode = dispatchData.getStartNode();
        if (StrUtil.isNotBlank(nodeStep) && (!nodeStep.equals(startNode.getBeanName()))) {
            return nodeStep;
        }
        return StartNodeStep.of(startNode).run(ThreadLocalUtil.get(ConditionContext.class));
    }

    /**
     * 执行服务 节点名字
     *
     * @param nodeId 节点名字
     * @return 下一个节点
     */
    private String doFlowNode(String nodeId) {
        if (Constant.EXIT.equals(nodeId)) {
            return Constant.EXIT;
        }
        FlowNodeStep nodeStep = dispatchData.getFlowMapping().get(nodeId);
        if (Objects.isNull(nodeStep)) {
            return nodeId;
        }
        this.flowStep = nodeId;
        nodeId = nodeStep.run(ThreadLocalUtil.get(ConditionContext.class));
        return doFlowNode(nodeId);
    }

    /**
     * 结束节点
     *
     * @param nodeStep 预设节点名称
     */
    private void doEndNode(String nodeStep) {
        EndNode endNode = dispatchData.getEndNode();
        if (ObjectUtil.isNull(endNode)) {
            return;
        }
        if (!endNode.getBeanName().equals(nodeStep)) {
            return;
        }
        EndNodeStep endNodeStep = EndNodeStep.of(endNode);
        endNodeStep.run(ThreadLocalUtil.get(ConditionContext.class));
    }
}
