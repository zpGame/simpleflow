package com.hunk.simpleflow;

import com.hunk.simpleflow.parse.SimpleFlowInitExecutor;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created on 2023/4/24.
 *
 * @author norbit
 *     <p>执行器初始化类
 */
public class SimpleFlowInit implements InitializingBean {

    private final SimpleFlowInitExecutor flowInitExecutor;

    public SimpleFlowInit(SimpleFlowInitExecutor flowInitExecutor) {
        this.flowInitExecutor = flowInitExecutor;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        flowInitExecutor.init();
    }
}
