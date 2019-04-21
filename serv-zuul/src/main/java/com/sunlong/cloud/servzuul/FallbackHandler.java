package com.sunlong.cloud.servzuul;

import com.alibaba.fastjson.JSON;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author : shipp
 * @description :
 * @data : 2018/11/21 10:08
 */
@Component
public class FallbackHandler implements FallbackProvider {
    @Override
    public String getRoute() {
        return "service-feign";
    }

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
                res.setCode(TravelConstants.GENERAL_RESPONSE_CODE_FAILED);
                res.setMsg("服务无响应");
                return new ByteArrayInputStream(JSON.toJSONString(res).getBytes("UTF-8"));
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }
        };
    }
}
