package com.ratel.hydra.common.configure;

import com.ratel.hydra.security.realm.HydraRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ratel
 * @date 2020-05-22
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public Realm realm(){
        HydraRealm realm = new HydraRealm();
        return realm;
    }

    @Bean
    public SecurityManager securityManager(Realm realm){
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //登录的 URL
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登录成功过跳转的URL
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权URL
        Map<String,String> filterChainMap = new LinkedHashMap<>();

        //静态资源 不拦截
        filterChainMap.put("/css/**","anon");
        filterChainMap.put("/js/**","anon");
        filterChainMap.put("/fonts/**","anon");
        filterChainMap.put("/img/**","anon");

        //druid 监控不拦截
        filterChainMap.put("/druid/**","anon");
        //登出接口
        filterChainMap.put("/logout","logout");

        //首页不拦截
        filterChainMap.put("/","anon");

        //剩下所有接口都需要拦截
//        filterChainMap.put("/**","authc");
        return shiroFilterFactoryBean;
    }


}
