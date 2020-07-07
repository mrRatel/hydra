package com.ratel.hydra.common.mapstruct;

import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.vo.RoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author ratel
 * @date 2020-06-12
 */
@Mapper(componentModel = "spring")
public interface RoleStruct {

    @Mapping(target = "enable",source = "enable")
    RoleVO convertToVo(Role role);

    List<RoleVO> convertToVo(List<Role> roleList);

    class CustomConvertor {
        protected static Byte booleanToByte(Boolean enable){
            return (byte) (enable ? 1 : 0);
        }
    }
}
