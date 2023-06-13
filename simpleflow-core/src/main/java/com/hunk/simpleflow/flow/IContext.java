package com.hunk.simpleflow.flow;

import java.io.Serializable;
import java.util.Map;

/**
 * Created on 2023/4/27.
 *
 * @author YCKJ4297
 *     <p>数据总线
 */
public interface IContext extends Serializable {

    /**
     * 获取Map容器的客户端请求参数集
     *
     * @return map
     */
    Map<String, String> getParamMap();

    /**
     * 设置参数
     *
     * @param key 键
     * @param value 值
     * @return boolean
     */
    IContext setParam(String key, String value);

    /**
     * 获取请求的参数
     *
     * @param key 键
     * @return 值
     */
    String getParam(String key);
}
