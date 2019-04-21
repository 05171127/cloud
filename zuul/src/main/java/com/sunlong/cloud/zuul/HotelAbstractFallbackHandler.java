package com.sunlong.cloud.zuul;

import com.alibaba.fastjson.JSON;
import com.sulong.cloud.common.model.GeneralResponse;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author : shipp
 * @description :
 * @data : 2018/12/28 11:09
 */
public abstract class HotelAbstractFallbackHandler implements FallbackProvider {

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }

            @Override
            public InputStream getBody() throws IOException {
                GeneralResponse res = new GeneralResponse();
                res.setCode((byte)1);
                res.setMsg("服务无响应");
                res.setData("");
                return new ByteArrayInputStream(JSON.toJSONString(res).getBytes("UTF-8"));
            }

            @Override
            public HttpStatus getStatusCode() {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }
        };
    }
}
