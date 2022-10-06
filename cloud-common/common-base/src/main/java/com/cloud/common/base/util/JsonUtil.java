package com.cloud.common.base.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author fangcy
 * @date 2022-10-03
 */
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public JsonUtil(){
        // 防止实例化工具类
        throw new AssertionError("No " + JsonUtil.class.getName() + " instances for you !");
    }

    /**
     * 对象转为json字符串
     * @param object
     * @return
     */
    public static String JsonStr(Object object){
        try {
            objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转对象
     * @param string
     * @param classType
     * @return
     */
    public static<T> Object fromJson(String string,Class<T> classType){
        try {
            return objectMapper.readValue(string,classType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
