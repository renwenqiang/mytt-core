package com.boot.mytt.core.redis.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.boot.mytt.core.util.CharsetUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {

    private Class<T> clazz;

    /**
     * 添加autotype白名单
     * 解决redis反序列化对象时报错 ：com.alibaba.fastjson.JSONException: autoType is not support
     */
    static {
        ParserConfig.getGlobalInstance().addAccept("com.skyworth");
    }

    public FastJson2JsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(CharsetUtils.CHARSET_UTF_8);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, CharsetUtils.CHARSET_UTF_8);
        return (T) JSON.parseObject(str, clazz);
    }

}
