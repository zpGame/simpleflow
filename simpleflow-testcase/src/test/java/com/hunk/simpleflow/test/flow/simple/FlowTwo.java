package com.hunk.simpleflow.test.flow.simple;

import com.hunk.simpleflow.flow.BaseNode;

import org.springframework.boot.test.context.TestComponent;

/**
 * Created on 2023/4/25.
 *
 * @author YCKJ4297
 *     <p>
 */
@TestComponent
public class FlowTwo extends BaseNode {
    @Override
    public void process() {
        System.out.println("flowTwo");
    }
}
