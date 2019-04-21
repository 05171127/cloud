package com.sunlong.cloud.zuul;

import org.springframework.stereotype.Component;

/**
 * @author : shipp
 * @description :
 * @data : 2018/11/21 10:08
 */
@Component
public class FallbackHandler extends HotelAbstractFallbackHandler {
    @Override
    public String getRoute() {
        return "hi";
    }

}
