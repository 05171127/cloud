package com.sunlong.cloud.eurekafeign;

import com.sulong.cloud.common.model.GeneralResponse;
import com.sulong.cloud.common.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author : shipp
 * @description :
 * @data : 2018/11/5 17:44
 */
@FeignClient(value = "hiac", fallbackFactory = FeignServiceImplA.class)
public interface FeignServiceB {

    @GetMapping(value = "/aa/bb/cc")
    String sayHiFromClientOne(@RequestParam(value = "ttt") String ttt);

    @GetMapping(value = "/aa/bb/dd")
    String sayHiFromClientOne1(@RequestParam(value = "ttt") String ttt);
}
