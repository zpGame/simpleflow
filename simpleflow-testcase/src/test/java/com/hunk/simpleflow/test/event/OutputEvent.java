package com.hunk.simpleflow.test.event;

import com.hunk.simpleflow.event.guava.CustomEvent;

/**
 * Created on 2023/5/22.
 *
 * @author norbit
 *     <p>
 */
public class OutputEvent extends CustomEvent {

    private String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "OutputEvent{" +
                "param='" + param + '\'' +
                '}';
    }
}
