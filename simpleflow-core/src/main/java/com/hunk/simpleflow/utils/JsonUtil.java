package com.hunk.simpleflow.utils;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hunk.simpleflow.consts.Constant;
import com.hunk.simpleflow.error.JsonProcessingException;

/**
 * Created on 2023/5/4.
 *
 * @author YCKJ4297
 *     <p>
 */
public class JsonUtil {

    private static ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //允许使用未带引号的字段名
        MAPPER.configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), Boolean.TRUE);
    }

    public static <T> T toObject(String json, Class<T> tClass) {
        try {
            return MAPPER.readValue(json, tClass);
        } catch (Exception e) {
            throw new JsonProcessingException(e.getMessage());
        }
    }

    public static <T> T toObject(String json, TypeReference<T> valueTypeRef) {
        try {
            return MAPPER.readValue(json, valueTypeRef);
        } catch (Exception e) {
            throw new JsonProcessingException(e.getMessage());
        }
    }

    public static <T> T toObject(String json, Class<?> aClass, Class<?> outClass) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(aClass, outClass);
        try {
            return MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            throw new JsonProcessingException(e.getMessage());
        }
    }

    public static <T> T toObject(ObjectNode objectNode, Class<T> tClass) {
        try {
            return MAPPER.treeToValue(objectNode, tClass);
        } catch (Exception e) {
            throw new JsonProcessingException(e.getMessage());
        }
    }

    public static String toJsonString(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            return Constant.EMPTY;
        }
    }

    public static ObjectNode toObjectNode(String json) {
        try {
            return (ObjectNode) MAPPER.readTree(json);
        } catch (Exception e) {
            return MAPPER.createObjectNode();
        }
    }

    public static ObjectNode toObjectNode(Object fromValue) {
        try {
            return MAPPER.convertValue(fromValue, ObjectNode.class);
        } catch (Exception e) {
            return MAPPER.createObjectNode();
        }
    }

    public static byte[] toBytes(Object value) {
        try {
            return MAPPER.writeValueAsBytes(value);
        } catch (Exception e) {
            throw new JsonProcessingException(e.getMessage());
        }
    }

}
