package com.hunk.simpleflow.utils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 2023/5/11.
 *
 * @author YCKJ4297
 *     <p>
 */
public final class ThreadLocalUtil {

    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL =
            ThreadLocal.withInitial(() -> new ConcurrentHashMap<>(16));

    public static Map<String, Object> getThreadLocal() {
        return THREAD_LOCAL.get();
    }

    public static <T> T get(Class<T> aClass) {
        return get(String.valueOf(aClass));
    }

    public static <T> T get(String key) {
        return CastUtils.cast(getThreadLocal().get(key));
    }

    public static <T> T get(String key, T defaultValue) {
        return null == getThreadLocal().get(key) ? defaultValue : get(key);
    }

    public static <T> T get(Class<T> aClass, T defaultValue) {
        return null == getThreadLocal().get(String.valueOf(aClass))
                ? defaultValue
                : get(String.valueOf(aClass));
    }

    public static <T> void set(T t) {
        set(String.valueOf(t.getClass()), t);
    }

    public static void set(String key, Object value) {
        getThreadLocal().put(key, value);
    }

    public static void set(Map<String, Object> keyValueMap) {
        getThreadLocal().putAll(keyValueMap);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

    public static <T> Map<String, T> fetchVarsByPrefix(String prefix) {
        Map<String, T> vars = new HashMap<>(16);
        if (prefix == null) {
            return vars;
        }
        for (Map.Entry<String, Object> entry : getThreadLocal().entrySet()) {
            String key = entry.getKey();
            if (key != null && key.startsWith(prefix)) {
                vars.put(key, CastUtils.cast(entry.getValue()));
            }
        }
        return vars;
    }

    public static <T> T remove(String key) {
        return CastUtils.cast(getThreadLocal().remove(key));
    }

    public static void clear(String prefix) {
        if (prefix == null) {
            return;
        }
        Map<String, Object> map = getThreadLocal();
        List<String> removeKeys = new ArrayList<>();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key != null && key.startsWith(prefix)) {
                removeKeys.add(key);
            }
        }
        for (String key : removeKeys) {
            map.remove(key);
        }
    }
}
