package com.sunlong.cloud.eurekaclient.mapper;

import com.sunlong.cloud.eurekaclient.dto.WenTest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : shipp
 * @description :
 * @data : 2018/12/11 17:27
 */
@Mapper
public interface WenTestMapper {

    List<WenTest> getAll();
}
