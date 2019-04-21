package com.sunlong.cloud.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;


/**
 *
 */
@EnableEurekaServer
@SpringBootApplication
@RestController
public class EurekaServerApplication {

    @Resource
    private JtSigner ddd;

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

    @GetMapping("/aa")
    public String ttt(@RequestParam("ttt") String ttt) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ff", "gg");
        map.put("aa", "bb");
        map.put("efef", "123");
        map.put("cc", "123");

        ddd.signature(map);
        return map.get("sign") + " , " + ttt;
    }

    @GetMapping("/abc")
    public String abc() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    @GetMapping("/bb")
    public String eeee(@RequestParam("ff") String ff, @RequestParam("aa") String aa,
                       @RequestParam("efef") String efef, @RequestParam("cc") String cc,
                       @RequestParam("sign") String sign) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ff", ff);
        map.put("aa", aa);
        map.put("efef", efef);
        map.put("cc", cc);
        map.put("sign", sign);

        return ddd.checkSign(map) ? "true" : "false";
    }
}
