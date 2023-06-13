package com.hunk.simpleflow.event.guava;

import com.hunk.simpleflow.error.CustomEventException;

import java.util.Map;

/**
 * Created on 2023/5/19.
 *
 * @author hunk
 *     <p>
 */
public interface CustomEventBus {

    /**
     * 发布一个消息
     *
     * @param event 事件
     * @return map
     * @throws CustomEventException 发布异常
     */
    Map<String, Object> publish(CustomEvent event) throws CustomEventException;

    /**
     * 注册eventBus
     *
     * @param customEventListen 监听器
     */
    void register(CustomEventListen<?> customEventListen);

    /**
     * 取消注册eventBus
     *
     * @param customEventListen 监听器
     */
    void unRegister(CustomEventListen<?> customEventListen);
}
