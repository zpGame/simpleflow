package com.hunk.simpleflow.base;

import com.hunk.simpleflow.flow.FlowNodeStep;
import com.hunk.simpleflow.flow.NodeInfo;
import com.hunk.simpleflow.flow.node.EndNode;
import com.hunk.simpleflow.flow.node.FlowNode;
import com.hunk.simpleflow.flow.node.StartNode;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created on 2023/5/18.
 *
 * @author YCKJ4297
 *     <p>
 */
@Getter
public class DispatchData {

    /** 开始节点 */
    private final StartNode startNode;

    /** 流程节点 */
    private final List<FlowNode> flowNodes;
    /** 节点序列 */
    private final Map<String, FlowNodeStep> flowMapping;
    /** 结束节点 */
    private final EndNode endNode;

    public static DispatchData of(NodeInfo nodeInfo) {
        return new DispatchData(
                nodeInfo.getStartNode(), nodeInfo.getFlowNodes(), nodeInfo.getEndNode());
    }

    public DispatchData(StartNode startNode, List<FlowNode> flowNodes, EndNode endNode) {
        this.startNode = startNode;
        this.flowNodes = flowNodes;
        this.endNode = endNode;
        this.flowMapping =
                flowNodes.stream()
                        .collect(Collectors.toMap(FlowNode::getBeanName, FlowNodeStep::of));
    }
}
