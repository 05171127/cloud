package com.sunlong.cloud.eurekafeign;

import com.sulong.cloud.common.model.GeneralResponse;
import com.sulong.cloud.common.model.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : shipp
 * @description :
 * @data : 2018/11/5 19:43
 */
@Component
@Slf4j
public class FeignServiceImplB implements FallbackFactory<FeignServiceB> {
    @Override
    public FeignServiceB create(Throwable cause) {
        return new FeignServiceB(){
            @Override
            public String sayHiFromClientOne(String ttt) {
                log.error("" , cause);
                return "error" + ttt;
            }

            @Override
            public String sayHiFromClientOne1(String ttt) {
                log.error("" , cause);
                return "error111" + ttt;
            }
        };
    }



}
