package com.sunlong.cloud.servzuul.support;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.Map;

/**
 * JtZuulFilterFactory
 * @author : shipp
 * @data : 2018/12/10 10:46
 */
@Getter
@Setter
public class JtZuulFilterFactory  {

    // filters
    private Map<String, JtZuulFilter> filters;

    // filterChainMap
    private Map<String, String> filterChainMap;

    /**
     * 获取filter
     * @param url
     * @return
     */
    public JtZuulFilter getFilter(String url) {
        String filterKey = null;

        PathMatcher matcher = new AntPathMatcher();
        for (Map.Entry<String, String> entry : filterChainMap.entrySet()) {
            if (matcher.match(entry.getKey(), url)) {
                filterKey = entry.getValue();
            }
        }

        if (StringUtils.isEmpty(filterKey)) return null;

        return filters.get(filterKey);
    }
}
