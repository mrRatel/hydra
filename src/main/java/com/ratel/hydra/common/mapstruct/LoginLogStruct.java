package com.ratel.hydra.common.mapstruct;

import com.ratel.hydra.common.utils.IpUtil;
import com.ratel.hydra.system.po.LoginLog;
import com.ratel.hydra.system.po.User;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;


/**
 * @author ratel
 * @date 2020-06-05
 */
@Mapper(componentModel = "spring")
public interface LoginLogStruct {

    @Mappings({
            @Mapping(target = "username",source = "user.username"),
            @Mapping(target = "userId",source = "user.id"),
            @Mapping(target = "loginTime",expression = "java(new java.util.Date())"),
            @Mapping(target = "ip",expression = "java(com.ratel.hydra.common.utils.IpUtil.getIpAddr(request))"),
//            @Mapping(target = "location",expression = "java (com.ratel.hydra.common.utils.IpUtil.getCityInfo(loginLog.ip))"),
            @Mapping(target = "device",expression = "java(com.ratel.hydra.common.utils.WebUtil.getOperatingSystem(request))"),
            @Mapping(target = "brower",expression = "java(com.ratel.hydra.common.utils.WebUtil.getBrowers(request))"),
    })
    LoginLog toLoginLog(User user,HttpServletRequest request);
}