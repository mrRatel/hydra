package com.ratel.hydra.security.filter;

import com.alibaba.fastjson.JSON;
import com.ratel.hydra.common.properties.ShiroProperty;
import com.ratel.hydra.common.utils.JwtTokenUtil;
import com.ratel.hydra.security.token.JwtToken;
import com.ratel.hydra.system.po.User;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.ehcache.impl.config.persistence.UserManagedPersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 登录过滤器
 * @author ratel
 * @date 2020-07-08
 */
@Slf4j
@Component
public class HydraAuthcFilter extends BasicHttpAuthenticationFilter {

    @Autowired
    private ShiroProperty shiroProperty;

    private AntPathMatcher antPathMatcher =new AntPathMatcher();

    /**
     * @Description 判断用户是否需要登录
     * @Author      ratel
     * @Date        2020/7/8
     * @param       request
     * @param       response
     * @return      boolean
     **/
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        //判断请求头中是否携带 指定 token
        HttpServletRequest req = (HttpServletRequest) request;

        //过滤无需登录的请求
        List<String> exclude = shiroProperty.getExclude();
        for (String uri : exclude) {
            if (antPathMatcher.match(uri,req.getRequestURI())) {
                return false;
            }
        }

        return true;
    }

    /**
     * @Description 验证登录
     * @Author      ratel
     * @Date        2020/7/8
     * @param       request
     * @param       response
     * @return      boolean
     **/
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        //获取token
        String token = getToken((HttpServletRequest) request);
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        Claims claims = JwtTokenUtil.parseToken(token);
        String str = (String) claims.get("user");
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        User user = JSON.parseObject(str, User.class);
        if (user == null){
            return false;
        }
        JwtToken jwtToken = new JwtToken(user.getUsername(),user.getPassword());

        //提交 realm 登录校验
        getSubject(request, response).login(jwtToken);

        return true;
    }


    /**
     * @Description 校验是否携带token
     * @Author      ratel
     * @Date        2020/7/8
     * @param       request
     * @param       response
     * @param       mappedValue
     * @return      boolean
     **/
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //如果请求不带 token 则直接跳过
        if (!isLoginAttempt(request, response)){
            return true;
        }

        //如果携带 token 则检查 token 有效性
        try {
            this.executeLogin(request, response);
        } catch (Exception e) {
            log.error("登录校验失败",e);
            return false;
        }
        return true;
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
