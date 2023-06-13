package com.hunk.simpleflow.test.flow.nocondition;

import com.hunk.simpleflow.flow.BaseNode;

import com.hunk.simpleflow.test.flow.param.TestResp;
import org.springframework.boot.test.context.TestComponent;

/**
 * Created on 2023/4/25.
 *
 * @author YCKJ4297
 *     <p>
 */
@TestComponent
public class FlowNoConditionOne extends BaseNode {
    @Override
    public void process() {
        TestResp beanByClass = getBeanByClass(TestResp.class);
        beanByClass.setRespOne("aaa");
        beanByClass.setRespTwo("bbb");
        System.out.println("FlowNoConditionOne");
    }
}
