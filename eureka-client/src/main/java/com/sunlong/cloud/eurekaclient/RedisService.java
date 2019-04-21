package com.sunlong.cloud.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author : shipp
 * @data : 2018/12/3 14:04
 */
@Service
public class RedisService {

    @Resource
    private RedisTemplate<String, ?> redisTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate2;

    public Set<byte[]> keys(String pattern) {
        return redisTemplate.execute((RedisCallback<Set<byte[]>>) connection -> {
            return connection.keys(pattern.getBytes());
        });
    }

    public boolean set(final String key, final String value, final Long expire) {

        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            connection.set(key.getBytes(), value.getBytes());
            if (expire > 0) {
                connection.expire(key.getBytes(), expire);
            }
            return true;
        });
    }

    public boolean mSet1(final Map<String, String> map, final Long expire) {

//        executePipelined
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                Long start = System.currentTimeMillis();
//                connection.openPipeline();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    connection.set(serializer.serialize(entry.getKey()), serializer.serialize(entry.getValue()));
                    if (expire > 0) {
                        connection.expire(serializer.serialize(entry.getKey()), expire);
                    }
                }
//                connection.mSet()
//                connection.closePipeline();

                Long end = System.currentTimeMillis();
                System.out.println(end - start);
                return true;
            }
        });
    }

    public boolean mSet(final Map<String, String> map, final Long expire) {

//        executePipelined
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
//            Long start = System.currentTimeMillis();
            connection.openPipeline();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (expire <= 0) {
                    connection.set(serializer.serialize(entry.getKey()), serializer.serialize(entry.getValue()));
                    continue;
                }

                connection.setEx(serializer.serialize(entry.getKey()), expire, serializer.serialize(entry.getValue()));
            }
            connection.closePipeline();
//            Long end = System.currentTimeMillis();
//            System.out.println(end - start);
            return true;
        });
    }



    /**
     * get
     * @param key
     * @return
     */
    public String get(final String key) {
        System.out.println(redisTemplate.getClientList());
        return redisTemplate.execute((RedisCallback<String>) connection -> new String(connection.get(key.getBytes())));
    }

    /**
     * del
     * @param key
     * @return
     */
    public Long del(final byte[]... key) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.del(key));
    }

    /**
     * exists
     * @param key
     * @return
     */
    public Boolean exists(final String key) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.exists(key.getBytes()));
    }

    /**
     * exists
     * @param key
     * @return
     */
    public Long incr(final String key) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.incr(key.getBytes()));
    }

    public Long sAdd(String key, String... values) {
        SetOperations<String, String> vo = redisTemplate2.opsForSet();
        return vo.add(key, values);
    }
    public String sPop(String key) {
        SetOperations<String, String> vo = redisTemplate2.opsForSet();
        return vo.pop(key);
    }


    public boolean setNx(final String key, final String value, final Long expire) {

        return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.set(key.getBytes(), value.getBytes(),
                Expiration.from(expire, TimeUnit.SECONDS), RedisStringCommands.SetOption.SET_IF_ABSENT));
    }

}
