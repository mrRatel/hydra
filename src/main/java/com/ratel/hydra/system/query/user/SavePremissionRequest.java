package com.ratel.hydra.system.query.user;

import com.ratel.hydra.common.pojo.BaseRequest;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author ratel
 * @date 2020-07-08
 */
@Data
@Accessors(chain = true)
public class SavePremissionRequest extends BaseRequest {
    /** 新增的角色ID */
    private List<Long> addRoleIds;
    /** 删除的角色ID */
    private List<Long> delRoleIds;
    /** 删除的权限ID */
    private List<Long> delPremissionIds;
    /** 新增的权限ID */
    private List<Long> addPremissionIds;

}
