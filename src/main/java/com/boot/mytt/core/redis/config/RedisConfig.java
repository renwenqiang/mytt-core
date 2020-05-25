package com.boot.mytt.core.redis.config;

import com.boot.mytt.core.redis.fastjson.FastJson2JsonRedisSerializer;
import com.boot.mytt.core.redis.util.FastJsonRedisUtils;
import com.boot.mytt.core.redis.util.JacksonRedisUtils;
import com.boot.mytt.core.redis.util.JdkRedisUtils;
import com.boot.mytt.core.redis.util.StringRedisUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;


/**
 * @author renwq
 * @date 2020/5/25
 */
@Configuration
public class RedisConfig {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * RedisTemplate配置(jdkSerialization)
     *
     * @return RedisTemplate<String, Object>
     */
    @Bean
    public RedisTemplate<String, Object> jdkRedisTemplate() {
        //String Serializer
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //Jdk Serializer
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //key Serializer
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(jdkSerializationRedisSerializer);
        //value Serializer
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * RedisTemplate配置(jackson2Json)
     *
     * @return RedisTemplate<String, Object>
     */
    @Bean
    public RedisTemplate<String, Object> jacksonRedisTemplate() {
        //String Serializer
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //Jackson Serializer
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //key
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        //value
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * RedisTemplate配置(fastJson2Json)
     *
     * @return RedisTemplate<String, Object>
     */
    @Bean
    public RedisTemplate<String, Object> fastJsonRedisTemplate() {
        //String Serializer
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //FastJson  Serializer
        FastJson2JsonRedisSerializer fastJson2JsonRedisSerializer = new FastJson2JsonRedisSerializer<>(Object.class);

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //key
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(fastJson2JsonRedisSerializer);
        //value
        redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public JdkRedisUtils jdkRedisUtils() {
        return new JdkRedisUtils(jdkRedisTemplate());
    }

    @Bean
    public JacksonRedisUtils jacksonRedisUtils() {
        return new JacksonRedisUtils(jacksonRedisTemplate());
    }

    @Bean
    public FastJsonRedisUtils fastJsonRedisUtils() {
        return new FastJsonRedisUtils(fastJsonRedisTemplate());
    }

    @Bean
    public StringRedisUtils stringRedisUtils() {
        return new StringRedisUtils(stringRedisTemplate);
    }
}
