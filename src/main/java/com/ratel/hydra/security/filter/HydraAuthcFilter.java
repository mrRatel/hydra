package com.ratel.hydra.security.filter;

import com.ratel.hydra.common.properties.ShiroProperty;
import com.ratel.hydra.common.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录过滤器
 * @author ratel
 * @date 2020-07-08
 */
@Component
public class HydraAuthcFilter extends FormAuthenticationFilter {

    @Autowired
    private ShiroProperty shiroProperty;
    /**
     * @Description 校验 Token 是否有效或过期
     * @Author      ratel
     * @Date        2020-07-08
     * @param       request
     * @param       response
     * @param       mappedValue
     * @return      boolean
     **/
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String token = getToken((HttpServletRequest)request);
        if (token == null){
            return false;
        }
        Claims claims = JwtTokenUtil.parseToken(token);
//        JwtTokenUtil.iis(claims.getExpiration())
        return true;
    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return  isLoginRequest(request,response) && isLoginSubmission(request,response);
    }


    private String getToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0){
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(shiroProperty.getTokenName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
