package com.hunk.simpleflow.test.flow.simple;

import com.hunk.simpleflow.flow.BaseNode;
import com.hunk.simpleflow.test.flow.param.ParamOne;
import com.hunk.simpleflow.test.flow.param.ParamTwo;

import org.springframework.boot.test.context.TestComponent;

/**
 * Created on 2023/4/25.
 *
 * @author norbit
 *     <p>
 */
@TestComponent
public class CheckFlow extends BaseNode {

    @Override
    public void process() {
        setConditionParam("paramOne", "false");
        setConditionParam("paramTwo", "1");
        System.out.println("checkFlow");
        System.out.println(getBeanByClass(ParamOne.class));
        System.out.println(getBeanByClass(ParamTwo.class));
//        throw new TestException("自定义异常");
    }
}
