package com.item.ecrew.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//将我们需要用到的转json格式的方法，写成一个工具类
public class ObjectMapperUtil {
    //接受object对象和response，两个参数
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static void writeValue(Object obj,HttpServletResponse response) throws IOException {
        //将object转成json字符串，然后返回给页面
        response.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(response.getOutputStream(),obj);
    }
    public static String writeValueAsString(Object obj) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(obj);
        return json;
    }
}
