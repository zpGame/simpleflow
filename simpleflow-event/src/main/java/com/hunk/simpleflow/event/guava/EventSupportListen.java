package com.hunk.simpleflow.event.guava;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created on 2023/5/19.
 *
 * @author norbit
 *     <p>
 */
public abstract class EventSupportListen<E extends CustomEvent>
        implements CustomEventListen<E>, InitializingBean {

    @Autowired
    private CustomEventBus eventBus;

    protected Map<String, Object> publish(E event) {
        return eventBus.publish(event);
    }

    @Override
    public void afterPropertiesSet() {
        eventBus.register(this);
    }
}
