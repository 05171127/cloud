package com.sunlong.cloud.serviceribbon;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : shipp
 * @description :
 * @data : 2018/11/5 16:15
 */
@RestController
public class RibbonController {

    @Resource
    private RibbonService ribbonService;

    @GetMapping("/ttt")
    public String test(@RequestParam("aaa") String aaa) {
        return ribbonService.hiService(aaa);
    }
}
