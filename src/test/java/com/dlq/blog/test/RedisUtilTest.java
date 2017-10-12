package com.dlq.blog.test;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * @author donglq
 * @date 2017/9/27 23:18
 */
public class RedisUtilTest {

    private Jedis redis;

    @Before
    public void init() {
        redis = new Jedis("39.108.95.218", 6379);
        String result = redis.auth("donglq0105");
        System.out.println("auth: " + result);
        System.out.println(">>>>>>>>>>>>>>>>> init complete <<<<<<<<<<<<<<<<<<<<<");
    }

    @Test
    public void test1() {
        while (true) {
            String key = "test_key";
//            boolean flag = limitQps(key);
//            System.out.println("allow access: " + flag);
            String result = limitQps(key, "1", "NX", "PX", 300);
            if("OK".equalsIgnoreCase(result)) {
                System.out.println("allow access");
            } else {
                System.out.println("forbid access");
            }
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
      * @param key
     * @param value
     * @param nxxx NX = 仅当key不存在时设置；XX = 仅当key存在时设置
     * @param expx EX = 秒；PX = 毫秒
     * @param time 过期时间
     * @return 设置成功返回“OK”
     */
    public String limitQps(final String key, final String value, final String nxxx, final String expx, final int time) {
        return redis.set(key, value, nxxx, expx, time);
    }

    /**
     * 每分钟限制访问数
     */
    private static final long limitPerMinute = 200;

    public boolean limitQps(final String key) {
        if(redis.exists(key)) {
            long count = redis.incr(key);
            System.out.println("count: " + count);
            if(count > limitPerMinute) {
                return false;
            } else {
                return true;
            }
        } else {
            redis.setex(key, 60, "1");
            return true;
        }
    }



}
