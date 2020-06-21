package com.ratel.hydra.common.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@Component
public class DateConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String s) {
        if (!StringUtils.isNoneBlank(s)) {
            return null;
        }
        log.info("Date format String to localDateTime");
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
