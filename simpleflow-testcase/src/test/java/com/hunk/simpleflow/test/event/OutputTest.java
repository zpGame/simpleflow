package com.hunk.simpleflow.test.event;

import com.hunk.simpleflow.event.NodeEvent;
import com.hunk.simpleflow.event.guava.CustomEventBus;
import com.hunk.simpleflow.event.guava.DefaultCustomEventBus;

import org.springframework.boot.test.context.TestComponent;

import jakarta.annotation.Resource;

/**
 * Created on 2023/6/16.
 *
 * @author norbit
 *     <p>
 */
@TestComponent
public class OutputTest implements NodeEvent {

    @Resource
    CustomEventBus customEventBus;

    @Override
    public void doEvent() {
        OutputEvent outputEvent = new OutputEvent();
        outputEvent.setParam("outputEvent");
        customEventBus.publish(outputEvent);
    }
}
