package com.hunk.simpleflow.utils;

import com.hunk.simpleflow.consts.Constant;

import java.math.BigDecimal;

/**
 * Created on 2023/4/24.
 *
 * @author norbit
 *     <p>自定义String工具
 */
public class StringUtil {

    public static String getString(Object o) {
        if (o instanceof String) {
            return ((String) o).trim();
        } else if (o instanceof BigDecimal) {
            return o.toString();
        }
        return isNull(String.valueOf(o), Constant.EMPTY);
    }
    /**
     * 如果对象为空，则以参数字符串值取代
     *
     * @param o key
     * @param defaultValue 默认值
     * @return String
     */
    public static String isNull(Object o, String defaultValue) {
        if (null == o || o.toString().length() == 0 || Constant.NULL.equals(o)) {
            return defaultValue;
        } else {
            return o.toString();
        }
    }
}
