package com.hunk.simpleflow.flow;

import com.hunk.simpleflow.utils.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 2023/4/27.
 *
 * @author norbit
 *         <p>
 */
public class ConditionContext implements IContext {

    private final Map<String, String> params;

    public static ConditionContext of() {
        return new ConditionContext();
    }

    public ConditionContext() {
        this.params = new ConcurrentHashMap<>();
    }

    @Override
    public Map<String, String> getParamMap() {
        return this.params;
    }

    @Override
    public ConditionContext setParam(String key, String value) {
        this.params.put(key, value);
        return this;
    }

    @Override
    public String getParam(String key) {
        return StringUtil.getString(params.get(key));
    }
}
