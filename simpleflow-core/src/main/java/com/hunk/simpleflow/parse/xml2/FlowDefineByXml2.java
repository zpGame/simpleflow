package com.hunk.simpleflow.parse.xml2;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.hunk.simpleflow.error.FileParseException;
import com.hunk.simpleflow.flow.*;
import com.hunk.simpleflow.flow.node.StartNode;

/**
 * Created on 2023/4/24.
 *
 * @author YCKJ4297
 *     <p>根据xml2文件解析
 */
public class FlowDefineByXml2 implements IFlowDefine {

    @Override
    public void parserDefine(String fileContent, IFlowDispatch flow) {

        FlowByXml2 of = FlowByXml2.of(fileContent);

        String flowName = of.getFlowName();

        if (StrUtil.isBlank(flowName)) {
            throw new FileParseException("No flowName found in xml2 file !");
        }

        StartNode startNode = of.getStartNode();

        if (ObjectUtil.isNull(startNode)) {
            throw new FileParseException("No startNode found in xml2 file !");
        }

        flow.setNodeInfo(NodeInfo.ofXml2(of));
    }
}
