package com.hunk.simpleflow.test.core;

import com.hunk.simpleflow.BaseServiceTest;
import com.hunk.simpleflow.flow.ConditionContext;
import com.hunk.simpleflow.flow.FlowResponse;
import com.hunk.simpleflow.flow.SimpleFlowExecutor;

import com.hunk.simpleflow.test.flow.param.ParamOne;
import com.hunk.simpleflow.test.flow.param.ParamTwo;
import com.hunk.simpleflow.test.flow.param.TestResp;
import com.hunk.simpleflow.test.flow.param.TestTwoResp;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created on 2023/4/27.
 *
 * @author YCKJ4297
 *     <p>
 */
public class FlowExecutorTest extends BaseServiceTest {

    @Resource SimpleFlowExecutor flowExecutor;

    @Test
    public void flow() {
        // empty (${paramOne}) or ${paramOne} ne 'true'
        FlowResponse execute =
                flowExecutor.executor("testProcess", ParamOne.of("aa"), ParamTwo.of("bb"));
        System.out.println(execute);
    }

    @Test
    public void flowNode() {
        ConditionContext context = ConditionContext.of().setParam("paramOne", "false").setParam("paramTwo", "1");
        FlowResponse execute =
                flowExecutor.executor(
                        "testProcess",
                        context,
                        "flowEnd",
                        ParamOne.of("aaaa"),
                        ParamTwo.of("bbbb"));
        System.out.println(execute);
    }

    @Test
    public void goOnFlow() {
        FlowResponse execute =
                flowExecutor.executor(
                        "testProcessNoCondition",
                        ConditionContext.of(),
                        "flowNoConditionTwo",
                        ParamOne.of("aa"),
                        ParamTwo.of("bb"));
        System.out.println(execute);
    }

    @Test
    public void flowNodeNoCondition() {
        FlowResponse execute =
                flowExecutor.executor(
                        "testProcessNoCondition", ParamOne.of("aa"), ParamTwo.of("bb"));
        System.out.println(execute);
    }

    @Test
    public void flowReq() {
        FlowResponse execute =
                flowExecutor.executor(
                        "testProcessNoCondition",
                        new Class[] {TestResp.class, TestTwoResp.class},
                        ParamOne.of("aa"),
                        ParamTwo.of("bb"));
        TestResp testResp = execute.getContextBean(TestResp.class);
        TestTwoResp twoResp = execute.getContextBean(TestTwoResp.class);
        System.out.println(testResp);
        System.out.println(twoResp);
        System.out.println(execute);
    }
}
