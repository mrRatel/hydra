package com.ratel.hydra.security.token;

import com.alibaba.fastjson.JSON;
import com.ratel.hydra.common.utils.JwtTokenUtil;
import com.ratel.hydra.system.po.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.mgt.AbstractRememberMeManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.subject.WebSubjectContext;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 负责 解密token  添加token 删除token
 * @author ratel
 * @date 2020-07-08
 */
@Slf4j
public class JWTTokenManager extends AbstractRememberMeManager {
    private static final String TOKEN_NAME = "hydraToken";
    private Cookie hydraToken;

    /**
     * @param subject
     * @return void
     * @Description 清除Token
     * @Author ratel
     * @Date 2020-07-08
     **/
    @Override
    protected void forgetIdentity(Subject subject) {
        if (WebUtils.isHttp(subject)) {
            HttpServletRequest request = WebUtils.getHttpRequest(subject);
            HttpServletResponse response = WebUtils.getHttpResponse(subject);
            hydraToken.removeFrom(request, response);
        }
    }

    /**
     * @param subject
     * @param serialized
     * @return void
     * @Description 登录成功后 添加Token
     * @Author ratel
     * @Date 2020-07-08
     **/
    @Override
    protected void rememberSerializedIdentity(Subject subject, byte[] serialized) {
        if (!WebUtils.isHttp(subject)) {
            log.debug("无法识别 web 环境");

        } else {
            HttpServletRequest request = WebUtils.getHttpRequest(subject);
            HttpServletResponse response = WebUtils.getHttpResponse(subject);

            //Base64 编码
//            String base64 = Base64.encodeToString(serialized);
            User user = JSON.parseObject(String.valueOf(serialized), User.class);
            Cookie template = this.getHydraToken();
            Cookie cookie = new SimpleCookie(template);

            //添加 Token
            Map<String, Object> payLoad = new HashMap<>(5);
            payLoad.put("uid", user.getId());
            payLoad.put("exp", null);
            payLoad.put("user", JSON.toJSONString(user));
            cookie.setValue(JwtTokenUtil.generatorToken(payLoad));
            cookie.saveTo(request, response);
        }
    }


    /**
     * @param subjectContext
     * @return byte[]
     * @Description 从请求头中获取 token 并解密
     * @Author ratel
     * @Date 2020-07-08
     **/
    @Override
    protected byte[] getRememberedSerializedIdentity(SubjectContext subjectContext) {
        if (!WebUtils.isHttp(subjectContext)) {
            log.debug("无法识别 web 环境");
            return null;
        }

        WebSubjectContext wsc = (WebSubjectContext) subjectContext;
        if (this.isIdentityRemoved(wsc)) {
            log.debug("删除 token ");
            return null;
        }

        HttpServletRequest request = WebUtils.getHttpRequest(wsc);
        HttpServletResponse response = WebUtils.getHttpResponse(wsc);
        //jwt
        String tokenValue = this.getHydraToken().readValue(request, response);
        if ("deleteMe".equals(tokenValue)) {
            return null;
        }

        //jwt 解密
        if (StringUtils.isNotEmpty(tokenValue)) {
//            Claims claims = JwtTokenUtil.parseToken(tokenValue);
//            Object user = claims.get("user");
            return tokenValue.getBytes();
        }

        return null;
    }

    /**
     * @param subjectContext
     * @return void
     * @Description 清除Token
     * @Author ratel
     * @Date 2020-07-08
     **/
    @Override
    public void forgetIdentity(SubjectContext subjectContext) {
        if (WebUtils.isHttp(subjectContext)) {
            HttpServletRequest request = WebUtils.getHttpRequest(subjectContext);
            HttpServletResponse response = WebUtils.getHttpResponse(subjectContext);
            hydraToken.removeFrom(request, response);
        }
    }


    private boolean isIdentityRemoved(WebSubjectContext subjectContext) {
        ServletRequest request = subjectContext.resolveServletRequest();
        if (request == null) {
            return false;
        } else {
            Boolean removed = (Boolean) request.getAttribute(ShiroHttpServletRequest.IDENTITY_REMOVED_KEY);
            return removed != null && removed;
        }
    }

    /**
     * @return
     * @Description 默认 Token 浏览器运行期间永久有效
     * @Author ratel
     * @Date 2020-07-08
     **/
    public JWTTokenManager() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setMaxAge(-1);
        simpleCookie.setHttpOnly(true);
        simpleCookie.setName(TOKEN_NAME);
        this.hydraToken = simpleCookie;
    }

    public JWTTokenManager(Cookie hydraToken) {
        this.hydraToken = hydraToken;
    }

    public Cookie getHydraToken() {
        return hydraToken;
    }

    public void setHydraToken(Cookie hydraToken) {
        this.hydraToken = hydraToken;
    }
}
