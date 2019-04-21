package com.sunlong.cloud.eurekaclient2;

import com.alibaba.fastjson.JSON;
import com.sulong.cloud.common.model.GeneralResponse;
import com.sulong.cloud.common.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@SpringBootApplication
@RestController
//@EnableAsync
public class Eurekaclient2Application {

    public static void main(String[] args) {
        SpringApplication.run(Eurekaclient2Application.class, args);
    }

    @Value("${server.port}")
    String port;

    @GetMapping("/aa/bb/cc")
    public String ttt(@RequestParam("ttt") String ttt) {

        System.out.println(Thread.currentThread().getName() + "," + ttt + "," + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port + " , " + ttt;
    }

    @GetMapping("/ddd")
    public ResponseEntity<String> ddd(@RequestParam("ttt") String ttt) {

        ResponseEntity entity = new ResponseEntity();
        entity.setCode("0");
        entity.setMessage("123");
        entity.setData("1235456");
        System.out.println(JSON.toJSONString(entity));
        return entity;
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

    @RequestMapping("/async/test")
    @ResponseBody
    public Callable<String> callable() {
        // 这么做的好处避免web server的连接池被长期占用而引起性能问题，
        // 调用后生成一个非web的服务线程来处理，增加web服务器的吞吐量。
        System.out.println("主线程开始..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
        Callable call = () -> {
            System.out.println("副线程开始..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
            Thread.sleep(10 * 1000L);
            System.out.println("副线程结束..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
            return "小单 - " + System.currentTimeMillis();
        };

        System.out.println("主线程结束..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
        return call;
    }

    @RequestMapping("/sync/test")
    @ResponseBody
    @Async
    public String sync() throws InterruptedException {
        // 这么做的好处避免web server的连接池被长期占用而引起性能问题，
        // 调用后生成一个非web的服务线程来处理，增加web服务器的吞吐量。
        System.out.println("主线程开始..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
        Thread.sleep(10 * 1000L);

        System.out.println("主线程结束..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
        return "123";
    }
}

