package com.hunk.simpleflow.flow;
/**
 * Created on 2023/4/25.
 *
 * @author YCKJ4297
 *     <p> 流程定义加载器
 */
public interface IFlowDefine {
    /**
     * 加载流程定义
     *
     * @param fileContent 文件内容
     * @param flow        执行
     */
    void parserDefine(String fileContent, IFlowDispatch flow);
}
