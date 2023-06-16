package com.hunk.simpleflow.flow;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;

import com.hunk.simpleflow.error.NoParamException;
import com.hunk.simpleflow.utils.ThreadLocalUtil;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Created on 2023/4/24.
 *
 * @author norbit
 *     <p>流程规则主要执行器类
 */
@Slf4j
public class SimpleFlowExecutor {

    public FlowResponse executor(String transCode, Object... contextBeans) {
        return executor(transCode, null, contextBeans);
    }

    /**
     * 执行flow
     *
     * @param transCode 流程名称
     * @param responseBeans 返回参数
     * @param contextBeans 入参
     * @return 执行结果
     */
    public FlowResponse executor(
            String transCode, Class<?>[] responseBeans, Object... contextBeans) {
        return executor(transCode, null, null, responseBeans, contextBeans);
    }

    public FlowResponse executor(
            String transCode, ConditionContext context, String nodeStep, Object... contextBeans) {
        return executor(transCode, context, nodeStep, null, contextBeans);
    }

    /**
     * 再执行flow
     *
     * @param transCode 流程名称
     * @param context 流转流程参数
     * @param nodeStep 流程节点
     * @param responseBeans 返回参数
     * @param contextBeans 入参
     * @return 执行结果
     */
    public FlowResponse executor(
            String transCode,
            ConditionContext context,
            String nodeStep,
            Class<?>[] responseBeans,
            Object... contextBeans) {
        if (ArrayUtil.isEmpty(contextBeans)) {
            throw new NoParamException("The parameter does not exist !");
        }
        Arrays.stream(ArrayUtil.defaultIfEmpty(responseBeans, new Class<?>[] {}))
                .map(ReflectUtil::newInstance)
                .forEach(ThreadLocalUtil::set);
        FlowResponse flowResponse;
        ThreadLocalUtil.set(null == context ? ConditionContext.of() : context);
        Arrays.stream(contextBeans).forEach(ThreadLocalUtil::set);
        try {
            flowResponse = FlowDispatch.of(transCode, responseBeans).execute(nodeStep);
        } finally {
            ThreadLocalUtil.remove();
        }
        return flowResponse;
    }
}
