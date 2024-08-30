package com.codelucky.spzx.service.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        ObjectMapper om = new ObjectMapper();
        // 指定一下序列化的域, 修饰符范围
        om.setVisibility(PropertyAccessor.ALL,
                         JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入类型, 类型为非final类型
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                                 ObjectMapper.DefaultTyping.NON_FINAL,
                                 JsonTypeInfo.As.PROPERTY);
        Jackson2JsonRedisSerializer<Object> JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(om,
                                                                                                    Object.class);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // value采用jackson序列化方式
        template.setValueSerializer(JsonRedisSerializer);
        // hash的key也采用String序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // hash的value采用jackson序列化方式
        template.setHashValueSerializer(stringRedisSerializer);
        return template;
    }
    
}
