package com.ratel.hydra.system.query.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author ratel
 * @date 2020-05-24
 */
@Data
public class UserLoginRequest {
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;
    @NotEmpty(message = "验证码不能为空")
    private String captcha;

    private boolean rememberMe;
}
