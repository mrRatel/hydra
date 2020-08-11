package com.ratel.hydra.common.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ratel
 * @date 2020/7/14
 */
@Slf4j
@Component
public class ViewDateConverter implements Converter<Date, String> {
    @Override
    public String convert(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        return simpleDateFormat.format(date);
    }
}
