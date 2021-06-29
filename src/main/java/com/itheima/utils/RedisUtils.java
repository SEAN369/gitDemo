package com.itheima.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class RedisUtils {

    private static JedisPoolConfig config = new JedisPoolConfig();
    private static JedisPool pool;

    static {

        /**
         * ResourceBundle 专门用来操作properties
         *   ResourceBundle.getBundle("jedis"); 获得配置文件 (此处不需要后缀)
         */
        //读取配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("redis");
        //创建配置
        config.setMinIdle(Integer.parseInt(bundle.getString("redis.minIdle"))); //最小空闲数
        config.setMaxWaitMillis(Integer.parseInt(bundle.getString("redis.maxWaitMillis"))); //最大连接时间
        config.setMaxTotal(Integer.parseInt(bundle.getString("redis.maxTotal")));//最大个数
        //1.创建池
        pool = new JedisPool( config,
                bundle.getString("redis.host") ,
                Integer.parseInt(bundle.getString("redis.port")) );
    }

    public static Jedis getJedis() {
        //2.取出连接对象
        return pool.getResource();
    }
}
