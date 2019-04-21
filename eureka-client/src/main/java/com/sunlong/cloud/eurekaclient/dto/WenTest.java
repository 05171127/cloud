package com.sunlong.cloud.eurekaclient.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * @author : shipp
 * @description :
 * @data : 2018/12/11 17:29
 */
@Getter
@Setter
public class WenTest {

    private int id;

    private int ev;

    private String eventId;

    private String eventName;
}
