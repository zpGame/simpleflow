package com.hunk.simpleflow.flow;

import com.hunk.simpleflow.flow.node.EndNode;
import com.hunk.simpleflow.flow.node.FlowNode;
import com.hunk.simpleflow.flow.node.StartNode;
import com.hunk.simpleflow.parse.xml.FlowByXml;
import com.hunk.simpleflow.parse.xml2.FlowByXml2;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Created on 2023/6/6.
 *
 * @author YCKJ4297
 *     <p>节点信息
 */
@Getter
@ToString
public class NodeInfo {

    private final String flowName;
    private final String flowDesc;
    private final StartNode startNode;
    private final List<FlowNode> flowNodes;
    private final EndNode endNode;

    public static NodeInfo ofXml(FlowByXml flowByXml) {
        return new NodeInfo(flowByXml);
    }

    public NodeInfo(FlowByXml flowByXml) {
        this.flowName = flowByXml.getFlowName();
        this.flowDesc = flowByXml.getFlowDesc();
        this.startNode = flowByXml.getStartNode();
        this.flowNodes = flowByXml.getFlowNodes();
        this.endNode = flowByXml.getEndNode();
    }

    public static NodeInfo ofXml2(FlowByXml2 flowByXml2) {
        return new NodeInfo(flowByXml2);
    }

    public NodeInfo(FlowByXml2 flowByXml2) {
        this.flowName = flowByXml2.getFlowName();
        this.flowDesc = flowByXml2.getFlowDesc();
        this.startNode = flowByXml2.getStartNode();
        this.flowNodes = flowByXml2.getFlowNodes();
        this.endNode = flowByXml2.getEndNode();
    }
}
