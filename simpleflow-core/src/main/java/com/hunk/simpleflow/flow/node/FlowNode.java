package com.hunk.simpleflow.flow.node;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created on 2023/4/27.
 *
 * @author norbit
 *     <p>flow 节点对象
 */
@Getter
@Setter
@ToString
public class FlowNode extends Node {

    private List<FlowWhen> flowWhens;

}
