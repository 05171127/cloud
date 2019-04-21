package com.sunlong.cloud.eurekaclient;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author : shipp
 * @description :
 * @data : 2019/3/26 10:02
 */
@Service
public class TestService {

    @Resource
    private RedisService redisService;

    @Transactional
    public String test() {
        redisService.set("aa", "aaa", 100L);
        redisService.set("bb", "bbb", 100L);
        redisService.set("cc", "ccc", 100L);
        return "123";
    }
}
