package com.hunk.simpleflow.event.guava;

import com.google.common.collect.Maps;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * Created on 2023/5/19.
 *
 * @author hunk
 *     <p>
 */
@Getter
@Setter
@ToString
public abstract class CustomEvent implements Serializable {

    private String eventName;

    private Map<String, Object> context = Maps.newHashMap();
}
