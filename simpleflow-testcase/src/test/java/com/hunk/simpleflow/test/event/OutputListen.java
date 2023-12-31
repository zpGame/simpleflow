package com.hunk.simpleflow.test.event;

import com.hunk.simpleflow.event.guava.EventSupportListen;
import org.springframework.boot.test.context.TestComponent;

import java.util.Map;

/**
 * Created on 2023/5/22.
 *
 * @author norbit
 *     <p>
 */
@TestComponent
public class OutputListen extends EventSupportListen<OutputEvent> {
    @Override
    public Map<String, Object> onEvent(OutputEvent paramE) {
        System.out.println(paramE.getParam());
        return null;
    }
}
