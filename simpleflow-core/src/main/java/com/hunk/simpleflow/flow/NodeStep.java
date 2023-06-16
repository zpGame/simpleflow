package com.hunk.simpleflow.flow;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * Created on 2023/5/18.
 *
 * @author norbit
 *     <p> 节点参数
 */
@Getter
@Builder
@ToString
public class NodeStep {

    private String flowName;

    private String flowStep;

    private Map<String, String> params;

}
