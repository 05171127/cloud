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
public class FeignServiceImplA implements FallbackFactory<FeignServiceA> {
    @Override
    public FeignServiceA create(Throwable cause) {
        return new FeignServiceA(){
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

            @Override
//    public String pptest(int id) {
            public String pptest(int id, String name) {
                return "pptest";
            }

            @Override
            public String pptest1(IdAndName user) {
                return "pptest1";
            }

            @Override
            public GeneralResponse aatest(User user) {
                GeneralResponse res = new GeneralResponse();
                res.setCode((byte)2);
                res.setMsg("feign catch");
                return res;
            }
        };
    }



}
