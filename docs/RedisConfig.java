package com.example.jedisdemo2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Bean
    public JedisCluster jedisCluster() {
        Set<HostAndPort> nodes = Arrays.stream(clusterNodes.split(","))
                .map(node -> {
                    String[] parts = node.split(":");
                    return new HostAndPort(parts[0], Integer.parseInt(parts[1]));
                })
                .collect(Collectors.toSet());
        //instantialise and return the JedisCluster

        return new JedisCluster(nodes);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(new JedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return redisTemplate;
    }
}
