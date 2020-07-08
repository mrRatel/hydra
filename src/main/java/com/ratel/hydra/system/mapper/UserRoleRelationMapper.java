package com.ratel.hydra.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ratel.hydra.system.po.UserRoleRelation;

import java.util.List;

public interface UserRoleRelationMapper extends BaseMapper<UserRoleRelation> {

    /**
     * @param userRoleRelations
     * @return void
     * @Description 批量新增用户和角色关系
     * @Author ratel
     * @Date 2020-07-08
     **/
    void insertList(List<UserRoleRelation> userRoleRelations);
}

