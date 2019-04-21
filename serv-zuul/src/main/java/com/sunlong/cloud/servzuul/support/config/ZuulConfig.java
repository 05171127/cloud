package com.sunlong.cloud.servzuul.support.config;

import com.sunlong.cloud.servzuul.support.AnonymousFilter;
import com.sunlong.cloud.servzuul.support.JtZuulFilter;
import com.sunlong.cloud.servzuul.support.JtZuulFilterFactory;
import com.sunlong.cloud.servzuul.support.UserFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : shipp
 * @description :
 * @data : 2018/12/10 11:39
 */
@Configuration
public class ZuulConfig {

    /**
     * 获取Filter
     * @author shipp
     * @date 2018/12/10 11:45
     * @param 
     * @return com.sunlong.cloud.servzuul.support.JtZuulFilterFactory
     */
    @Bean
    public JtZuulFilterFactory jtZuulFilterFactory() {
        JtZuulFilterFactory filter = new JtZuulFilterFactory();

        Map<String, JtZuulFilter> filters = new HashMap<>(16);
        filters.put("user", new UserFilter());
        filters.put("anon", new AnonymousFilter());

        LinkedHashMap<String, String> filterChainMap = new LinkedHashMap<>();
        filterChainMap.put("/feign/cc","anon");
        filterChainMap.put("/feign/dd","user");
        filterChainMap.put("/feign/**","anon");

        filter.setFilterChainMap(filterChainMap);
        filter.setFilters(filters);
        return filter;
    }
}
