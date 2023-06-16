package com.hunk.simpleflow.event;
/**
 * Created on 2023/5/19.
 *
 * @author norbit
 *     <p>节点事件接口
 */
public interface NodeEvent {

    void preEvent();

    void afterEvent();
}
