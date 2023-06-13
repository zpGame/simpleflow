package com.hunk.simpleflow.parse.xml;

import cn.hutool.core.collection.CollUtil;

import com.hunk.simpleflow.error.FileParseException;
import com.hunk.simpleflow.flow.node.EndNode;
import com.hunk.simpleflow.flow.node.FlowNode;
import com.hunk.simpleflow.flow.node.FlowWhen;
import com.hunk.simpleflow.flow.node.StartNode;
import com.hunk.simpleflow.utils.Builder;

import lombok.Getter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created on 2023/4/27.
 *
 * @author YCKJ4297
 *     <p>
 */
@Getter
public class FlowByXml {

    private final String flowName;

    private final String flowDesc;

    private final StartNode startNode;

    private final EndNode endNode;

    private final List<FlowNode> flowNodes;

    public static FlowByXml of(String fileContent) {
        return new FlowByXml(fileContent);
    }

    Function<Element, FlowNode> createFlowNode =
            node ->
                    Builder.of(FlowNode::new)
                            .width(FlowNode::setBeanName, node.attributeValue("beanId"))
                            .width(FlowNode::setPreEvent, node.attributeValue("preEvent"))
                            .width(FlowNode::setAfterEvent, node.attributeValue("afterEvent"))
                            .width(FlowNode::setNodeDesc, node.attributeValue("nodeDesc"))
                            .width(FlowNode::setFlowWhens, obtainWhens(node))
                            .build();

    Function<Element, List<FlowNode>> ordinaryNodes =
            nodes ->
                    Optional.ofNullable(nodes.elements("node"))
                            .filter(CollUtil::isNotEmpty)
                            .orElse(Collections.emptyList())
                            .stream()
                            .map(createFlowNode)
                            .collect(Collectors.toList());

    Function<Element, StartNode> start =
            node -> Optional.ofNullable(node).map(o ->
                    Builder.of(StartNode::new)
                            .width(StartNode::setBeanName, o.attributeValue("beanId"))
                            .width(StartNode::setPreEvent, o.attributeValue("preEvent"))
                            .width(StartNode::setAfterEvent, o.attributeValue("afterEvent"))
                            .width(StartNode::setNodeDesc, o.attributeValue("nodeDesc"))
                            .width(StartNode::setFlowWhens, obtainWhens(o))
                            .build()).orElse(null);

    Function<Element, EndNode> end =
            node -> Optional.ofNullable(node).map( o ->
                    Builder.of(EndNode::new)
                            .width(EndNode::setBeanName, o.attributeValue("beanId"))
                            .width(EndNode::setPreEvent, o.attributeValue("preEvent"))
                            .width(EndNode::setAfterEvent, o.attributeValue("afterEvent"))
                            .width(EndNode::setNodeDesc, o.attributeValue("nodeDesc"))
                            .build()).orElse(null);

    public FlowByXml(String fileContent) {
        Document document = strToDoc(fileContent);
        Element rootElement = document.getRootElement();
        Element nodes = rootElement.element("nodes");
        this.flowName = rootElement.attributeValue("flowName");
        this.flowDesc = rootElement.attributeValue("flowDesc");
        this.startNode = start.apply(nodes.element("start"));
        this.flowNodes = ordinaryNodes.apply(nodes);
        this.endNode = end.apply(nodes.element("end"));
    }

    private Document strToDoc(String fileContent) {
        try {
            return DocumentHelper.parseText(fileContent);
        } catch (Exception e) {
            throw new FileParseException("xml file parsing exception !");
        }
    }

    private List<FlowWhen> obtainWhens(Element node) {
        return Optional.ofNullable(node.element("choose"))
                .map(choose -> choose.elements("when"))
                .orElse(Collections.emptyList()).stream()
                .map(when -> Builder.of(FlowWhen :: new)
                        .width(FlowWhen::setCondition, when.attributeValue("condition"))
                        .width(FlowWhen::setTo, when.attributeValue("to"))
                        .width(FlowWhen::setConditionDesc, when.attributeValue("conditionDesc"))
                        .build()).
                collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "FlowByXml{" + "flowDesc='" + flowDesc + '\'' + ", flowNodes=" + flowNodes + '}';
    }
}
