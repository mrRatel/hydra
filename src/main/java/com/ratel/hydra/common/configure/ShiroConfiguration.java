package com.ratel.hydra.common.configure;

import com.alibaba.fastjson.JSON;
import com.ratel.hydra.common.properties.ShiroProperty;
import com.ratel.hydra.common.utils.JwtTokenUtil;
import com.ratel.hydra.security.filter.HydraAuthcFilter;
import com.ratel.hydra.security.realm.HydraRealm;
import com.ratel.hydra.security.token.JWTTokenManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ratel
 * @date 2020-05-22
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(ShiroProperty.class)
public class ShiroConfiguration {

    @Bean
    public Realm realm() {
        HydraRealm realm = new HydraRealm();
        return realm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultSecurityManager securityManager(Realm realm) {
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setRememberMeManager(initJWTTokenManager());
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, ShiroProperty shiroProperty, HydraAuthcFilter hydraAuthcFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //登录的 URL
        shiroFilterFactoryBean.setLoginUrl("/login.html");

        //登录成功过跳转的URL
        //shiroFilterFactoryBean.setSuccessUrl("/admin/index.html");

        //未授权URL
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");


        //静态资源
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        log.debug("exclude premission url {}", shiroProperty.getExclude().toString());
        shiroProperty.getExclude().forEach(a -> filterChainMap.put(a, "anon"));

//        //静态资源 不拦截
//        filterChainMap.put("/css/**","anon");
//        filterChainMap.put("/js/**","anon");
//        filterChainMap.put("/fonts/**","anon");
//        filterChainMap.put("/img/**","anon");
//        filterChainMap.put("/*.html","anon");
//        filterChainMap.put("/assets/**","anon");
//        filterChainMap.put("/admin/**","anon");
//        filterChainMap.put("/component/**","anon");
//        //验证码 不拦截
//        filterChainMap.put("/image/captcha","anon");
//
//        filterChainMap.put("/error/**","anon");
//
//        //druid 监控不拦截
//        filterChainMap.put("/druid/**","anon");
//        //登录接口
//        filterChainMap.put("/user/login","anon");
//        //注册
//        filterChainMap.put("/user/add","anon");
//        filterChainMap.put("/","anon");


        //登出接口
        filterChainMap.put("/logout", "logout");

        //剩下所有接口都需要拦截
        //filterChainMap.put("/**", "user");

        //添加JWT过滤器
        Map<String, Filter> filterMap = new HashMap<>(3);
        filterMap.put("hydra", hydraAuthcFilter);
        shiroFilterFactoryBean.setFilters(filterMap);
        //剩下所有接口都需要拦截
        filterChainMap.put("/**", "hydra");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);

        //关闭自带 shiro session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        return shiroFilterFactoryBean;
    }

    public SimpleCookie rememberMeCookie() {
        //设置cookie名称 对应 <input type="checkbox" name= "HydraRememberMe"/>
        SimpleCookie cookie = new SimpleCookie("HydraToken");
        //指定cookie过期时间 单位s
        cookie.setMaxAge(-1);
        return cookie;
    }


    /*public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie 加密的秘钥
        Map<String,Object> payLoad = new HashMap<>();
        payLoad.put("uid",user.getId());
        payLoad.put("exp",null);
        payLoad.put("user", JSON.toJSONString(user));
        Cookie token = new Cookie("token", JwtTokenUtil.generatorToken(payLoad));
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }*/

    /**
     * @return com.ratel.hydra.security.token.JWTTokenManager
     * @Description JWT token manager
     * 负责 解密token  添加token 删除token
     * @Author ratel
     * @Date 2020-07-08
     **/
    private JWTTokenManager initJWTTokenManager() {
        return new JWTTokenManager(rememberMeCookie());
    }

    /**
     * @Description 指定默认代理 为cglib
     * @Author      ratel
     * @Date        2020/7/8
     * @return      org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
     **/
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }
}
