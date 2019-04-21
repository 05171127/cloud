package com.sunlong.cloud.eurekaclient2;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : shipp
 * @description :
 * @data : 2019/1/3 9:45
 */
@Getter
@Setter
public class UserInfo {

    private String uid;

    private Integer cnt;

    private String createTime;

    @Override
    public String toString() {
        return uid + '\t' + createTime + '\t' + cnt;
    }
}
