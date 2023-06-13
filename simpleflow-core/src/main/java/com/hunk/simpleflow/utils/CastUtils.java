package com.hunk.simpleflow.utils;
/**
 * Created on 2023/5/11.
 *
 * @author YCKJ4297
 *     <p>object to bean
 */
public interface CastUtils {
    /**
     * 类转换
     *
     * @param <T> 类型
     * @param object 对象
     * @return 转换后的对象
     */
    @SuppressWarnings("unchecked")
    static <T> T cast(Object object) {
        return (T) object;
    }
}
