package com.hunk.simpleflow.flow.node;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created on 2023/6/2.
 *
 * @author YCKJ4297
 *     <p>开始节点
 */
@Getter
@Setter
@ToString
public class StartNode extends Node {

    private List<FlowWhen> flowWhens;

}
