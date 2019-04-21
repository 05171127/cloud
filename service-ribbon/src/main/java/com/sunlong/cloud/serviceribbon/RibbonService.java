package com.sunlong.cloud.serviceribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author : shipp
 * @description :
 * @data : 2018/11/5 16:09
 */
@Service
public class RibbonService {

    @Resource
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "err")
    public String hiService(String name) {
        return restTemplate.getForObject("http://hi/aa/bb/cc?ttt=" + name, String.class);
    }

    public String err(String name) {
        return "sorry," + name + ", error";
    }
}
