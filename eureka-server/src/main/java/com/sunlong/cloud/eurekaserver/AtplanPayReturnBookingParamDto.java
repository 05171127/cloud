package com.sunlong.cloud.eurekaserver;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Robb
 * @Description: atplanPay,预授权申请返回参数
 * @date 2018/7/24 15:46
 */
@Getter
@Setter
public class AtplanPayReturnBookingParamDto {

    /**
     * 订单号
     */
    private String agencyRef;
    /**
     * 交易金额
     */
    private String amt;
    /**
     * 交易流水号
     */
    private String ap;
    /**
     * 获预授权返回code
     */
    private String code;
    /**
     * 获取预授权返回状态
     */
    private String status;

    /**
     * 参数签名
     */
    private String sign;

}
