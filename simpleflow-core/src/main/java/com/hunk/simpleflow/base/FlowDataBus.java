package com.hunk.simpleflow.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2023/5/18.
 *
 * @author YCKJ4297
 *     <p>流程初始化数据
 */
public class FlowDataBus {

    static Map<String, DispatchData> dataMap = new HashMap<>(16);

    public static Map<String, DispatchData> getDataMap() {
        return dataMap;
    }

    public static DispatchData getByFlowName(String flowName) {
        return dataMap.get(flowName);
    }

    public static void setDataMap(Map<String, DispatchData> dataMap) {
        FlowDataBus.dataMap = dataMap;
    }

    public static void setDataMap(String transCode, DispatchData dispatchData) {
        dataMap.put(transCode, dispatchData);
    }
}
