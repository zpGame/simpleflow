package com.hunk.simpleflow.flow;

import com.hunk.simpleflow.error.NoSuchContextBeanException;
import com.hunk.simpleflow.utils.CastUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Created on 2023/5/4.
 *
 * @author YCKJ4297
 *     <p>执行结果
 */
@Getter
@Builder
@ToString
public class FlowResponse {

    private boolean success;

    private Exception exception;

    private NodeStep nodeStep;

    private List<Object> objects;

    public <T> T getContextBean(Class<T> contextBeanClazz) {
        T t =
                CastUtils.cast(
                        objects.stream()
                                .filter(o -> o.getClass().equals(contextBeanClazz))
                                .findFirst()
                                .orElse(null));
        if (t == null) {
            throw new NoSuchContextBeanException("this type is not in the context type passed in");
        }
        return t;
    }

    public static FlowResponse success() {
        return FlowResponse.builder().success(Boolean.TRUE).build();
    }

    public static FlowResponse success(List<Object> objects) {
        return FlowResponse.builder().success(Boolean.TRUE).objects(objects).build();
    }

    public static FlowResponse fail(Exception exception, NodeStep nodeStep) {
        return FlowResponse.builder()
                .success(Boolean.FALSE)
                .exception(exception)
                .nodeStep(nodeStep)
                .build();
    }
}
