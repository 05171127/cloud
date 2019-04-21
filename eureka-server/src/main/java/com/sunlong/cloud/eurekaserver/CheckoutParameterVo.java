package com.sunlong.cloud.eurekaserver;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CheckoutParameterVo {

    private String orderId;

    private String agency;

    private BigDecimal amt;

    private String currency;

    private String billInfo;

    private String returnUrl;

    private String authProcessUrl;

    private String orderSource;

    private String paymentTimeLimit;

    private String sign;

}
