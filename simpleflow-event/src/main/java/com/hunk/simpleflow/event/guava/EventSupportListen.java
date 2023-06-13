package com.hunk.simpleflow.event.guava;

import org.springframework.beans.factory.InitializingBean;

import java.util.Map;

import javax.annotation.Resource;

/**
 * Created on 2023/5/19.
 *
 * @author hunk
 *     <p>
 */
public abstract class EventSupportListen<E extends CustomEvent>
        implements CustomEventListen<E>, InitializingBean {

    @Resource private CustomEventBus eventBus;

    protected Map<String, Object> publish(E event) {
        return this.eventBus.publish(event);
    }

    @Override
    public void afterPropertiesSet() {
        this.eventBus.register(this);
    }
}
