package com.hunk.simpleflow.test.flow.simple;

import com.hunk.simpleflow.flow.BaseNode;

import org.springframework.boot.test.context.TestComponent;

/**
 * Created on 2023/4/25.
 *
 * @author norbit
 *     <p>
 */
@TestComponent
public class FlowOne extends BaseNode {
    @Override
    public void process() {
        System.out.println("flowOne");
    }
}
