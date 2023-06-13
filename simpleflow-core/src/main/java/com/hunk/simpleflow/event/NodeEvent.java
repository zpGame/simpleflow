package com.hunk.simpleflow.event;
/**
 * Created on 2023/5/19.
 *
 * @author YCKJ4297
 *     <p>节点事件接口
 */
public interface NodeEvent {

    void preEvent();

    void afterEvent();
}
