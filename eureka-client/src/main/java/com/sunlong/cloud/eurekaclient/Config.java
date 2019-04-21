package com.sunlong.cloud.eurekaclient;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author : shipp
 * @description :
 * @data : 2018/12/11 15:56
 */
@Component
//@AutoConfigureOrder(Integer.MAX_VALUE)
public class Config {
//    @Bean
    public TTTT tttta(){
        return new TTTT();
    }

//    @Bean
//    @ConditionalOnBean(RedisTemplate.class)
    public TTTT tttt(){
        return new TTTT();
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setEnableTransactionSupport(true);//打开事务支持
        return template;
    }
}
