package com.hunk.simpleflow.event.guava;

import com.hunk.simpleflow.error.CustomEventException;

import java.util.Map;

/**
 * Created on 2023/5/19.
 *
 * @author norbit
 *     <p>异步事件
 */
public class AsyncCustomEventBus implements CustomEventBus {
    @Override
    public Map<String, Object> publish(CustomEvent event) throws CustomEventException {
        return null;
    }

    @Override
    public void register(CustomEventListen<?> customEventListen) {}

    @Override
    public void unRegister(CustomEventListen<?> customEventListen) {}
}
