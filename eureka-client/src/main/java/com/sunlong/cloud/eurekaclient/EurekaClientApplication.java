package com.sunlong.cloud.eurekaclient;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.sunlong.cloud.eurekaclient.dto.WenTest;
import com.sunlong.cloud.eurekaclient.mapper.WenTestMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@EnableEurekaClient
@SpringBootApplication
@RestController
//@CX
//@MapperScan(basePackages = {"ssss.redis"})
//@EnableCircuitBreaker
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @Resource
    private RedisService service;

    @Resource
    private TestService testService;

    @Resource
    private WenTestMapper dao;

    private Random random = new Random();

    @GetMapping("/test8")
    public String test8() {
        Boolean ret = service.setNx("shippex","123123",180L);
        return ret ? "ok" : "no";
    }

    @GetMapping("/test7")
    public String test7() {
        Random random = new Random();
        List<String> list = Stream.generate(() -> random.nextInt(10)).limit(10).map(String::valueOf).collect(Collectors.toList());

        service.sAdd("aaaaa", list.toArray(new String[10]));
        return "ok";
    }

    @GetMapping("/test5")
    public String test5() {
        Set<byte[]> set = service.keys("hotel:*");
        if (set.size() == 0) return "0";
        System.out.println(set.size());
        byte[][] arr = set.toArray(new byte[set.size()][]);
        service.del(arr);
        return String.valueOf(set.size());
    }

    @GetMapping("/test4")
    public String test4(@RequestParam("key") String key) {
        if (!key.contains(",")) {
            ttest1(key);
        } else {
            String[] ss = key.split(",");
            Arrays.stream(ss).forEach(this::ttest1);
        }

        return "ok";
    }

    @GetMapping("/test6")
    public String test6(@RequestParam("key") String key) {
        ttest2(key);
        return "ok";
    }

    @GetMapping("/test3")
    public String test3(@RequestParam("count") int count) {
        for (int i = 0; i < count; i++) {
            new Thread(this::ttest).start();
        }
        return "ok";
    }

    @GetMapping("/test2")
    public String test2() {
        List<WenTest> list = dao.getAll();
        return String.valueOf(list.size());
    }
    @GetMapping("/test")
    public String test() {

        Map<String, String> map = new HashMap<>(2000);
        for(int i = 0; i < 2000; i++) {
            map.put("shipp-" + i, "b" + i);
        }
        Long start = System.currentTimeMillis();
        service.mSet(map, 200L);
        System.out.println("1111-"+ (System.currentTimeMillis() - start));

        Map<String, String> map1 = new HashMap<>(2000);
        for(int i = 0; i < 2000; i++) {
            map1.put("shipp-a-" + i, "b" + i);
        }
        start = System.currentTimeMillis();
        service.mSet(map1, 200L);
        System.out.println("2222-" +(System.currentTimeMillis() - start));
        return "11122";
    }

    @GetMapping("/test10")
    public String test10() {
        return testService.test();
    }

    private void ttest1(String tt) {

//        Map<String, String> map = new HashMap<>(2000);
        List<String> list = new ArrayList<>(3000);
        for(int i = 0; i < 3000; i++) {
            list.add("shipp-" + tt + "-" + i);
        }
        byte[][] arr = new byte[3000][];
        for (int i = 0; i < 3000; i++) {
            arr[i] = list.get(i).getBytes();
        }
        service.del(arr);
    }

    @GetMapping("/test1")
    public String test1() {

        Map<String, String> map = new HashMap<>(2000);
        for(int i = 0; i < 3000; i++) {
            map.put("a" + i, "b" + i);
        }
        Long start = System.currentTimeMillis();
        service.mSet(map, 200L);
        System.out.println("a1111-"+ (System.currentTimeMillis() - start));

        Map<String, String> map1 = new HashMap<>(20000);
        for(int i = 0; i < 3000; i++) {
            map1.put("b" + i, "a" + i);
        }
        start = System.currentTimeMillis();
        service.mSet1(map1, 200L);
        System.out.println("a2222-" +(System.currentTimeMillis() - start));
//        return service.get("ddd");
//        byte[][] keys = new byte[2][];
//        keys[0] = "aaa".getBytes();
//        keys[1] = "bbb".getBytes();
//        return service.del(keys).toString();
        return "aaa";
    }

    private void ttest2(String tt) {
        Map<String, String> map = new HashMap<>(2000);
        for(int i = 0; i < 3000; i++) {
            map.put("shipp-" + tt + "-" + i, "b" + i);
        }
        Long start = System.currentTimeMillis();
        service.mSet(map, 200L);
        Long end = System.currentTimeMillis();
        System.out.println("start-" + tt + "---" + start);
        System.out.println("end-" + tt + "---" +  end);
        System.out.println("spend-" + tt + "---" +  (end - start));
    }

    private void ttest() {
        int tt = random.nextInt(1000);
        Map<String, String> map = new HashMap<>(3000);
        for(int i = 0; i < 3000; i++) {
            map.put("shipp-" + tt + "-" + i, "b" + i);
        }
        Long start = System.currentTimeMillis();
        service.mSet(map, 200L);
        Long end = System.currentTimeMillis();
        System.out.println("start-" + tt + "---" + start);
        System.out.println("end-" + tt + "---" +  end);
        System.out.println("spend-" + tt + "---" +  (end - start));
    }


    @GetMapping("/aa/bb/cc")
    public String ttt(@RequestParam("ttt") String ttt) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "," + ttt + "," + System.currentTimeMillis());
        //3秒没有触发服务降级
        //Thread.sleep(3000);
        //5秒触发了服务降级
        //Thread.sleep(5000);
        return port + " , " + ttt;
    }

    @GetMapping("/aa/bb/dd")
    public String ttt1(@RequestParam("ttt") String ttt) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "," + ttt + "," + System.currentTimeMillis());
        Thread.sleep(1000);
        return port + " aa , " + ttt;
    }

    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
