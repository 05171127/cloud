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
@FeignClient(value = "hia", fallbackFactory = FeignServiceImplA.class)
public interface FeignServiceA {

    @GetMapping(value = "/aa/bb/cc")
    String sayHiFromClientOne(@RequestParam(value = "ttt") String ttt);

    @GetMapping(value = "/aa/bb/dd")
    String sayHiFromClientOne1(@RequestParam(value = "ttt") String ttt);

    @RequestMapping(value = "/pp/test")
    String pptest(@RequestParam("id") int id, @RequestParam("name") String name);
//    String pptest(@RequestParam(value = "id") int id);

    @RequestMapping(value = "pp/test1")
    String pptest1(@RequestBody IdAndName user);

    @PostMapping(value = "/aa/test")
    GeneralResponse aatest(@Validated @RequestBody User user);
//    String pptest(@RequestParam(value = "id") int id);
}
