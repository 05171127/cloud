package com.sunlong.cloud.servzuul;

import org.springframework.stereotype.Component;

/**
 * @author : shipp
 * @description :
 * @data : 2018/11/5 19:43
 */
@Component
public class AuthServiceImpl implements AuthService {


    @Override
    public GeneralResponse getAuth(AuthenticateReq req) {
        return GeneralResponse.failed();
    }
}
