package com.ratel.hydra.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ratel.hydra.common.mapstruct.RoleStruct;
import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.query.PageQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

 public interface RoleService extends IBaseServiceImpl<Role> {
     Role baseGetById(Long id);

     void baseDelById(Long id);

     IPage<Role> basePage(PageQuery<Role> query);

     void baseAddOrUpdate(Role role);


     void batchDelByIds(List<Long> ids);
}
