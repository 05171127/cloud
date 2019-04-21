package com.sunlong.cloud.eurekaclient1;

import com.sulong.cloud.common.model.GeneralResponse;
import com.sulong.cloud.common.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

//@EnableEurekaClient
@SpringBootApplication
@RestController
@EnableAsync
public class EurekaClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClient1Application.class, args);
    }

    @Value("${server.port}")
    String port;

    @GetMapping("/aa/bb/cc")
    public String ttt(@RequestParam("ttt") String ttt) {

        System.out.println(Thread.currentThread().getName() + "," + ttt + "," + System.currentTimeMillis());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port + " cc , " + ttt;
    }

    @GetMapping("/aa/bb/dd")
    public String ttt1(@RequestParam("ttt") String ttt) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "," + ttt + "," + System.currentTimeMillis());
        Thread.sleep(1000);
        return port + " aa , " + ttt;
    }

    @PostMapping("/aa/test")
    public GeneralResponse<String> aatest(@Validated @RequestBody User user) {

        GeneralResponse res = new GeneralResponse();
        res.setCode((byte)1);
        res.setMsg("请求成功");

        System.out.println(Thread.currentThread().getName() + "," + System.currentTimeMillis());
        String ss = user.toString();
        System.out.println(ss);
        res.setData(port + " , " + ss);
        return res;
    }

    @PostMapping("/pp/test")
    public String posttest(@RequestParam("id") int id, @RequestParam("name") String name) {

        return id + name;
    }

    @PostMapping("/pp/test1")
    public String posttest1(@RequestBody IdAndName user) {

        return user.getId() + user.getName();
    }

    @RequestMapping("/async/test")
    @ResponseBody
    public Callable<String> callable() {
        // 这么做的好处避免web server的连接池被长期占用而引起性能问题，
        // 调用后生成一个非web的服务线程来处理，增加web服务器的吞吐量。
        return () -> {
            Thread.sleep(10 * 1000L);
            return "小单 - " + System.currentTimeMillis();
        };
    }


}
