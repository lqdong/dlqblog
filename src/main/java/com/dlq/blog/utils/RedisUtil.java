package com.dlq.blog.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;


/**
 * redis工具
 * @author donglq
 * @date 2017/09/27 23:00
 */
@Service
public class RedisUtil {

    @Value("${redis.host:39.108.95.218}")
    private String host;

    @Value("${redis.port:6379}")
    private int port;

    private Jedis jedis;

    @PostConstruct
    public void init() {
        jedis = new Jedis(host, port);
    }

    public Jedis getJedis() {
        return jedis;
    }

}
