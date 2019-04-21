package com.sunlong.cloud.eurekafeign;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author : shipp
 * @description :
 * @data : 2018/11/5 20:14
 */
//@Configuration
public class FeignConfig {
//    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, SECONDS.toMillis(100), 5);
    }

//    @Bean
    public Request.Options options() {
        return new Request.Options(3000, 3000);
    }
}
