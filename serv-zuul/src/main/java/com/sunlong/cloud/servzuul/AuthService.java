package com.sunlong.cloud.servzuul;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : shipp
 * @description :
 * @data : 2018/11/5 17:44
 */
@FeignClient(value = "auth", fallback = AuthServiceImpl.class)
public interface AuthService {

    @GetMapping(value = "/auth")
    GeneralResponse getAuth(@RequestBody AuthenticateReq req);
}
