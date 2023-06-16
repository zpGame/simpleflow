package com.hunk.simpleflow.event;

import com.hunk.simpleflow.enums.EventEnum;
import com.hunk.simpleflow.event.guava.AsyncCustomEventBus;
import com.hunk.simpleflow.event.guava.CustomEventBus;
import com.hunk.simpleflow.event.guava.DefaultCustomEventBus;
import com.hunk.simpleflow.property.SimpleFlowConfig;

/**
 * Created on 2023/6/16.
 *
 * @author norbit
 *     <p>
 */
public class EventBusInit {

    public static CustomEventBus of(SimpleFlowConfig property) {
        if (EventEnum.guava_sync.equals(property.getEventEnum())) {
            return new DefaultCustomEventBus();
        } else {
            return new AsyncCustomEventBus()
                    .createThread(
                            property.getNameFormat(),
                            property.getCorePoolSize(),
                            property.getMaximumPoolSize(),
                            property.getKeepAliveTime(),
                            property.getCapacity());
        }
    }
}
