package com.hunk.simpleflow.event.guava;

import com.google.common.eventbus.EventBus;
import com.hunk.simpleflow.error.CustomEventException;

import java.util.Map;

/**
 * Created on 2023/5/19.
 *
 * @author hunk
 *     <p>同步事件
 */
public class DefaultCustomEventBus implements CustomEventBus {

    private final EventBus eventBus = new EventBus();

    @Override
    public Map<String, Object> publish(CustomEvent customEvent) throws CustomEventException {
        try {
            this.eventBus.post(customEvent);
        } catch (Exception e) {
            throw new CustomEventException("publish event error", e);
        }
        return customEvent.getContext();
    }

    @Override
    public void register(CustomEventListen<?> eventListener) {
        this.eventBus.register(eventListener);
    }

    @Override
    public void unRegister(CustomEventListen<?> eventListener) {
        this.eventBus.unregister(eventListener);
    }
}
