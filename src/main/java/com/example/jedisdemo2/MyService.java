package com.example.jedisdemo2;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@Service
public class MyService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MyService.class);
//    @Autowired
//    private JedisCluster jedisCluster;
//
//    public void setValue(String key, Object value) {
//        jedisCluster.set(key, value.toString());
//    }
//
//    public Object getValue(String key) {
//        return jedisCluster.get(key);
//    }



    //connect to jedis node and set key
    public void setValue(String key, Object value) {
        //connect to jedis cluster with auth

        Jedis jedis = new Jedis("mytest", 20179);
        jedis.set(key, value.toString());
    }





}
