package com.sulong.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : shipp
 * @description :
 * @data : 2018/11/21 14:40
 */
@RestController
public class TestController {

    @Value("${foo}")
    private String foo;

    @GetMapping("foo")
    public String getFoo() {
        return this.foo;
    }
}
