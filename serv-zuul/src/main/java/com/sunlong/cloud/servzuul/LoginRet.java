package com.sunlong.cloud.servzuul;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : shipp
 * @data : 2018/12/3 16:36
 */
@Getter
@Setter
public class LoginRet {
    // 状态
    private Byte status;

    // 尝试次数
    private long times;

    // token
    private String token;

    /**
     * constructor
     * @author shipp
     * @date 2018/12/3 16:42
     * @param 
     * @return 
     */
    public LoginRet() {
        this.setTimes(0L);
        this.setToken("");
    }
}
