package com.hunk.simpleflow.parse;

import com.hunk.simpleflow.base.DispatchData;
import com.hunk.simpleflow.base.FlowDataBus;
import com.hunk.simpleflow.enums.FileTypeEnum;
import com.hunk.simpleflow.flow.*;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2023/4/24.
 *
 * @author YCKJ4297
 *     <p>服务编排组件
 */
@Slf4j
public class FlowDispatchInit implements IFlowDispatch {

    private final String fileContent;

    private final IFlowDefine flowDefine;

    private NodeInfo nodeInfo;

    @Override
    public NodeInfo getNodeInfo() {
        return nodeInfo;
    }

    @Override
    public void setNodeInfo(NodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    public static FlowDispatchInit of(String fileContent, FileTypeEnum fileType) {
        return new FlowDispatchInit(fileContent, fileType);
    }

    public FlowDispatchInit(String fileContent, FileTypeEnum fileType) {
        this.fileContent = fileContent;
        this.flowDefine = fileType.getFlowDefine().get();
    }

    @Override
    public void init() {
        flowDefine.parserDefine(fileContent, this);
        String flowName = nodeInfo.getFlowName();
        FlowDataBus.setDataMap(flowName, DispatchData.of(nodeInfo));
    }
}
