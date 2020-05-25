package com.boot.mytt.core.util;

import com.boot.mytt.core.exception.UtilException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class JacksonUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
            @Override
            public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                String format = value.atZone(ZoneOffset.UTC).format(LocalDateUtils.DATETIME_FORMATTER);
                gen.writeString(format);
            }
        });

        javaTimeModule.addSerializer(LocalDate.class, new JsonSerializer<LocalDate>() {
            @Override
            public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                String format = value.format(LocalDateUtils.DATE_FORMATTER);
                gen.writeString(format);
            }
        });
        javaTimeModule.addSerializer(LocalTime.class, new JsonSerializer<LocalTime>() {
            @Override
            public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                String format = value.format(LocalDateUtils.TIME_FORMATTER);
                gen.writeString(format);
            }
        });

        javaTimeModule.addDeserializer(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                return LocalDateTime.parse(jsonParser.getText(), LocalDateUtils.DATETIME_FORMATTER);
            }
        });

        javaTimeModule.addDeserializer(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                return LocalDate.parse(jsonParser.getText(), LocalDateUtils.DATE_FORMATTER);
            }
        });

        javaTimeModule.addDeserializer(LocalTime.class, new JsonDeserializer<LocalTime>() {
            @Override
            public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                return LocalTime.parse(jsonParser.getText(), LocalDateUtils.TIME_FORMATTER);
            }
        });

        objectMapper.registerModule(javaTimeModule);
    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * 转换为 JSON 字符串
     *
     * @param obj 对象
     * @return json字符串
     */
    public static String obj2json(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new UtilException("JSON转换错误");
        }
    }

    /**
     * 转换为 JSON 字符串，忽略空值
     *
     * @param obj 对象
     * @return json字符串
     */
    public static String obj2jsonIgnoreNull(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new UtilException("JSON转换错误");
        }
    }

    /**
     * 转换为 JavaBean
     *
     * @param jsonString json字符串
     * @param clazz      对象class
     * @param <T>        泛型
     * @return 对象
     */
    public static <T> T json2pojo(String jsonString, Class<T> clazz) {
        try {
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            return objectMapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            throw new UtilException("JSON转换错误");
        }
    }

    /**
     * 字符串转换为 Map<String, Object>
     *
     * @param jsonString json字符串
     * @param <T>        泛型
     * @return map
     */
    public static <T> Map<String, T> json2map(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.readValue(jsonString, Map.class);
        } catch (Exception e) {
            throw new UtilException("JSON转换错误");
        }
    }

    /**
     * 字符串转换为 Map<String, T>
     *
     * @param jsonString json字符串
     * @param clazz      对象class
     * @param <T>        泛型
     * @return map
     */
//    public static <T> Map<String, T> json2map(String jsonString, Class<T> clazz) {
//        try {
//            Map<String, Map<String, Object>> map = objectMapper.readValue(jsonString, new TypeReference<Map<String, T>>() {
//            });
//            Map<String, T> result = new HashMap<String, T>();
//            for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
//                result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
//            }
//            return result;
//        } catch (Exception e) {
//            throw new UtilException("JSON转换错误");
//        }
//    }

    /**
     * 深度转换 JSON 成 Map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> json2mapDeeply(String json) {
        try {
            return json2MapRecursion(json, objectMapper);
        } catch (Exception e) {
            throw new UtilException("JSON转换错误");
        }
    }

    /**
     * 把 JSON 解析成 List，如果 List 内部的元素存在 jsonString，继续解析
     *
     * @param json
     * @param mapper
     * @return
     */
    private static List<Object> json2ListRecursion(String json, ObjectMapper mapper) {
        try {
            if (StringUtils.isEmpty(json)) {
                return null;
            }

            List<Object> list = mapper.readValue(json, List.class);

            for (Object obj : list) {
                if (obj != null && obj instanceof String) {
                    String str = (String) obj;
                    if (str.startsWith("[")) {
                        obj = json2ListRecursion(str, mapper);
                    } else if (obj.toString().startsWith("{")) {
                        obj = json2MapRecursion(str, mapper);
                    }
                }
            }

            return list;
        } catch (Exception e) {
            throw new UtilException("JSON转换错误");
        }
    }

    /**
     * 把 JSON 解析成 Map，如果 Map 内部的 Value 存在 jsonString，继续解析
     *
     * @param json
     * @param mapper
     * @return
     */
    private static Map<String, Object> json2MapRecursion(String json, ObjectMapper mapper) {
        try {
            if (StringUtils.isEmpty(json)) {
                return null;
            }
            Map<String, Object> map = mapper.readValue(json, Map.class);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object obj = entry.getValue();
                if (obj != null && obj instanceof String) {
                    String str = ((String) obj);

                    if (str.startsWith("[")) {
                        List<?> list = json2ListRecursion(str, mapper);
                        map.put(entry.getKey(), list);
                    } else if (str.startsWith("{")) {
                        Map<String, Object> mapRecursion = json2MapRecursion(str, mapper);
                        map.put(entry.getKey(), mapRecursion);
                    }
                }
            }
            return map;
        } catch (Exception e) {
            throw new UtilException("JSON转换错误");
        }
    }

    /**
     * 将 JSON 数组转换为集合
     *
     * @param jsonArrayStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) {
        try {
            JavaType javaType = getCollectionType(ArrayList.class, clazz);
            List<T> list = (List<T>) objectMapper.readValue(jsonArrayStr, javaType);
            return list;
        } catch (Exception e) {
            throw new UtilException("JSON转换错误");
        }
    }


    /**
     * 获取泛型的 Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 将 Map 转换为 JavaBean
     *
     * @param map
     * @param clazz
     * @return
     */
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    /**
     * 将 Map 转换为 JSON
     *
     * @param map
     * @return json字符串
     */
    public static String mapToJson(Map map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            throw new UtilException("JSON转换错误");
        }
    }

    /**
     * 将 JSON 对象转换为 JavaBean
     *
     * @param obj   对象
     * @param clazz 对象class
     * @return 对象
     */
    public static <T> T obj2pojo(Object obj, Class<T> clazz) {
        return objectMapper.convertValue(obj, clazz);
    }
}
