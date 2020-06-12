package com.ratel.hydra.common.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.ratel.hydra.common.mapstruct.RoleStruct;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Component
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        if (StringUtils.isEmpty(s)){
            return null;
        }
        return Date.from(LocalDateTime.parse(s).atZone(ZoneId.systemDefault()).toInstant());
    }

}
