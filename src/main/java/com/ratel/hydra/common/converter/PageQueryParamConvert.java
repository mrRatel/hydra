package com.ratel.hydra.common.converter;

import com.ratel.hydra.system.query.PageQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author ratel
 * @date 2020-06-11
 */
@Slf4j
public class PageQueryParamConvert {

    public static PageQuery convert(PageQuery query){
        Map queryParam = query.getQueryParam();
        if (!CollectionUtils.isEmpty(queryParam)){
            try {
                Object o = query.getQuery().getClass().newInstance();
                BeanUtils.populate(o,queryParam);
            } catch (Exception e) {
                log.error("bean 字段拷贝异常",e);
            }
        }
        return query;
    }
}
