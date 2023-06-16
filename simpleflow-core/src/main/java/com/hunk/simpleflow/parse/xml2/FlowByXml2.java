package com.hunk.simpleflow.parse.xml2;

import cn.hutool.core.util.ObjectUtil;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created on 2023/4/28.
 *
 * @author norbit
 *     <p>
 */
@Getter
public class FlowByXml2 {

    private final String flowName;

    private final String flowDesc;

    private final StartNode startNode;

    private final EndNode endNode;

    private final List<FlowNode> flowNodes;

    public static FlowByXml2 of(String fileContent) {
        return new FlowByXml2(fileContent);
    }

    private StartNode parseStartNode(Element node, Map<String, List<FlowWhen>> flowWhens) {
        if (ObjectUtil.isNull(node)) {
            return null;
        }
        String beanId = node.attributeValue("beanId");
        return Builder.of(StartNode::new)
                .width(StartNode::setBeanName, beanId)
                .width(StartNode::setPreEvent, node.attributeValue("preEvent"))
                .width(StartNode::setAfterEvent, node.attributeValue("afterEvent"))
                .width(StartNode::setNodeDesc, node.attributeValue("nodeDesc"))
                .width(StartNode::setFlowWhens, flowWhens.get(beanId))
                .build();
    }

    Function<Element, EndNode> parseEndNode =
            node -> Optional.ofNullable(node).map( o ->
                    Builder.of(EndNode::new)
                            .width(EndNode::setBeanName, o.attributeValue("beanId"))
                            .width(EndNode::setPreEvent, o.attributeValue("preEvent"))
                            .width(EndNode::setAfterEvent, o.attributeValue("afterEvent"))
                            .width(EndNode::setNodeDesc, o.attributeValue("nodeDesc"))
                            .build()).orElse(null);

    private List<FlowNode> parseNode(List<Element> nodes, Map<String, List<FlowWhen>> flowWhens) {
        return nodes.stream()
                .map(
                        node -> {
                            String beanId = node.attributeValue("beanId");
                            return Builder.of(FlowNode::new)
                                    .width(FlowNode::setBeanName, node.attributeValue("beanId"))
                                    .width(FlowNode::setPreEvent, node.attributeValue("preEvent"))
                                    .width(
                                            FlowNode::setAfterEvent,
                                            node.attributeValue("afterEvent"))
                                    .width(FlowNode::setNodeDesc, node.attributeValue("nodeDesc"))
                                    .width(FlowNode::setFlowWhens, flowWhens.get(beanId))
                                    .build();
                        })
                .collect(Collectors.toList());
    }

    Function<Element, FlowWhen> convertLine =
            line ->
                    Builder.of(FlowWhen::new)
                            .width(FlowWhen::setFrom, line.attributeValue("from"))
                            .width(FlowWhen::setTo, line.attributeValue("to"))
                            .width(FlowWhen::setCondition, line.attributeValue("condition"))
                            .width(FlowWhen::setConditionDesc, line.attributeValue("conditionDesc"))
                            .build();

    Function<List<Element>, Map<String, List<FlowWhen>>> parseLine =
            lines ->
                    lines.stream().map(convertLine).collect(Collectors.toList()).stream()
                            .collect(Collectors.groupingBy(FlowWhen::getFrom));

    public FlowByXml2(String fileContent) {
        Document document = strToDoc(fileContent);
        Element rootElement = document.getRootElement();
        this.flowDesc = rootElement.attributeValue("flowDesc");
        this.flowName = rootElement.attributeValue("flowName");
        List<Element> lines = rootElement.element("lines").elements("line");
        Map<String, List<FlowWhen>> flowWhens = parseLine.apply(lines);
        Element element = rootElement.element("nodes");
        this.startNode = parseStartNode(element.element("start"), flowWhens);
        this.endNode = parseEndNode.apply(element.element("end"));
        this.flowNodes = parseNode(element.elements("node"), flowWhens);
    }

    private Document strToDoc(String fileContent) {
        try {
            return DocumentHelper.parseText(fileContent);
        } catch (Exception e) {
            throw new FileParseException("xml file parsing exception !");
        }
    }

    @Override
    public String toString() {
        return "FlowByXml2{" + "flowDesc='" + flowDesc + '\'' + ", flowNodes=" + flowNodes + '}';
    }
}
