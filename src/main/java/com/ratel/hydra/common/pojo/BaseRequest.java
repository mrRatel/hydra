package com.ratel.hydra.common.pojo;

import com.ratel.hydra.system.po.User;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ratel
 * @date 2020-07-08
 */
@Data
@Accessors(chain = true)
public class BaseRequest {
    /** 用户 */
    private User user;
}
