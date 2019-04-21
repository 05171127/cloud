package com.sunlong.cloud.eurekafeign;

import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.sulong.cloud.common.model.GeneralResponse;
import com.sulong.cloud.common.model.User;
import feign.Feign;
import io.micrometer.core.instrument.Meter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : shipp
 * @description :
 * @data : 2018/11/5 17:46
 */
@RestController
public class FeighController {

    @Resource
    private FeignService feignService;

    @Resource
    private FeignServiceA feignServiceA;

    @Resource
    private FeignServiceB feignServiceB;

    @GetMapping(value = "/ttt")
    public String ttt(@RequestParam("aaa") String aa) {
        Random random = new Random();

//        feignService.sayHiFromClientOne(aa  + "," + random.nextInt(100));
//        System.out.println("aaaa" + System.currentTimeMillis());
        return feignService.sayHiFromClientOne(aa  + "," + random.nextInt(100));
    }

    @GetMapping(value = "/pool")
    public String pool(@RequestParam("count") int cnt) {
        Random random = new Random();
//        ExecutorService service = Executors.newFixedThreadPool(20);
        for (int i = 0; i < cnt; i++) {
            if (i % 2 == 0) {
//                HystrixThreadPoolProperties.class;
//                service.execute(() -> System.out.println(feignService.sayHiFromClientOne(cnt  + "," + random.nextInt(100)) + "  " + System.currentTimeMillis()));
                new Thread(() -> System.out.println(feignService.sayHiFromClientOne(cnt  + "," + random.nextInt(100)) + "  " + System.currentTimeMillis())).start();
            } else if (i % 2 == 1){
//                service.execute(() -> System.out.println(feignService.sayHiFromClientOne1(cnt  + "," + random.nextInt(100)) + "  " + System.currentTimeMillis()));
                new Thread(() -> System.out.println(feignServiceA.sayHiFromClientOne(cnt  + "," + random.nextInt(100)) + "  " + System.currentTimeMillis())).start();
            }
//            else {
//                new Thread(() -> System.out.println(feignServiceB.sayHiFromClientOne(cnt  + " BB," + random.nextInt(100)) + "  " + System.currentTimeMillis())).start();
//            }

        }
        return "123";
    }

    @GetMapping(value = "/aaa")
    public GeneralResponse aaa() {
        User user = new User();
        user.setId(100);
        user.setName("111");
        user.setPassword("ab123456");

//        feignService.sayHiFromClientOne(aa  + "," + random.nextInt(100));
//        System.out.println("aaaa" + System.currentTimeMillis());
//        return feignService.pptest(id);
        return feignService.aatest(user);
    }

    @GetMapping(value = "/ddd")
    public String ddd(@RequestParam("id") int id, @RequestParam("name") String name) {
        Random random = new Random();

//        feignService.sayHiFromClientOne(aa  + "," + random.nextInt(100));
//        System.out.println("aaaa" + System.currentTimeMillis());
//        return feignService.pptest(id);
        return feignService.pptest(id, name);
    }

    @PostMapping(value = "/eee")
    public String eee(@RequestParam("id") int id, @RequestParam("name") String name) {
        Random random = new Random();

//        feignService.sayHiFromClientOne(aa  + "," + random.nextInt(100));
//        System.out.println("aaaa" + System.currentTimeMillis());
        IdAndName user = new IdAndName();
        user.setId(id);
        user.setName(name);
//        Feign.Builder
        return feignService.pptest1(user);
    }
}
