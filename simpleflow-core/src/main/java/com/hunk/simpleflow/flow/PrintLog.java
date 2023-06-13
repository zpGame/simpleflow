package com.hunk.simpleflow.flow;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2023/5/10.
 *
 * @author YCKJ4297
 *     <p>
 */
@Slf4j
public class PrintLog {

    private static final String DIVIDED_LINE = "-------------------------";

    public static void printLog(IContext ctx, String serviceName) {
        log.info(
                "\n{}{}参数区开始{}\n param={}.\n{}{}参数区结束{}\n",
                DIVIDED_LINE,
                serviceName,
                DIVIDED_LINE,
                ctx.getParamMap().toString(),
                DIVIDED_LINE,
                serviceName,
                DIVIDED_LINE);
    }
    
}
