package com.ratel.hydra.system.vo;

import com.ratel.hydra.system.po.User;
import lombok.Data;

import java.util.List;

/**
 * @author ratel
 * @date 2020/7/5
 */
@Data
public class SavePremissionVO {
    private List<Long> roleIds;
    private List<Long> premissionIds;
    private User currentUser;
}
