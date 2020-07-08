package com.ratel.hydra.security.token;

import com.ratel.hydra.system.po.User;
import org.apache.shiro.authc.AuthenticationToken;

/**
 *
 * @author ratel
 * @date 2020/7/8
 */
public class JwtToken implements AuthenticationToken {
    private String token;
    private String username;
    private String password;
    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    public JwtToken(String token) {
        this.token = token;
    }

    public JwtToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public JwtToken() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
