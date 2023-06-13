package com.hunk.simpleflow.parse.xml;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.hunk.simpleflow.error.FileParseException;
import com.hunk.simpleflow.flow.*;
import com.hunk.simpleflow.flow.node.StartNode;

/**
 * Created on 2023/4/27.
 *
 * @author YCKJ4297
 *     <p>根据xml文件解析
 */
public class FlowDefineByXml implements IFlowDefine {

    @Override
    public void parserDefine(String fileContent, IFlowDispatch flow) {

        FlowByXml of = FlowByXml.of(fileContent);

        String flowName = of.getFlowName();

        if (StrUtil.isBlank(flowName)) {
            throw new FileParseException("No flowName found in xml file !");
        }

        StartNode startNode = of.getStartNode();

        if (ObjectUtil.isNull(startNode)) {
            throw new FileParseException("No startNode found in xml file !");
        }

        flow.setNodeInfo(NodeInfo.ofXml(of));
    }
}
