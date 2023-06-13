package com.hunk.simpleflow.flow;

/**
 * Created on 2023/4/24.
 *
 * @author YCKJ4297
 *     <p>
 */
public interface IFlowDispatch {

    /** 初始化 */
    void init();

    /**
     * 获取流程名称
     *
     * @return 节点信息
     */
    NodeInfo getNodeInfo();

    /**
     * 设置流程节点
     *
     * @param nodeInfo 节点信息
     */
    void setNodeInfo(NodeInfo nodeInfo);
}
