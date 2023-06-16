package com.hunk.simpleflow.enums;

import com.hunk.simpleflow.event.guava.AsyncCustomEventBus;
import com.hunk.simpleflow.event.guava.CustomEventBus;
import com.hunk.simpleflow.event.guava.DefaultCustomEventBus;

import lombok.Getter;

import java.util.function.Supplier;

/**
 * Created on 2023/6/16.
 *
 * @author norbit
 *     <p>EventBus
 */
public enum EventEnum {

    /** eventBus同步 */
    guava_sync(DefaultCustomEventBus::new),

    /** eventBus异步 */
    guava_async(AsyncCustomEventBus::new);

    @Getter private final Supplier<CustomEventBus> flowDefine;

    EventEnum(Supplier<CustomEventBus> flowDefine) {
        this.flowDefine = flowDefine;
    }
}
