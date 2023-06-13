package com.hunk.simpleflow.event.guava;

import java.util.Map;

/**
 * Created on 2023/5/19.
 *
 * @author hunk
 *     <p>
 */
public interface CustomEventListen<E> {

    /**
     * 执行流程
     *
     * @param paramE 参数
     * @return map
     */
    Map<String, Object> onEvent(E paramE);
}
