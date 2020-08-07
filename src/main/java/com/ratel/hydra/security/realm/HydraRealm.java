package com.ratel.hydra.security.realm;

import com.alibaba.fastjson.JSON;
import com.ratel.hydra.common.utils.JwtTokenUtil;
import com.ratel.hydra.security.token.JwtToken;
import com.ratel.hydra.system.po.Menu;
import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.service.MenuService;
import com.ratel.hydra.system.service.RoleService;
import com.ratel.hydra.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.ratel.hydra.common.constant.ExceptionEnum.AUTH1005;

/**
 * @author ratel
 * @date 2020-05-22
 */
@Slf4j
public class HydraRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private UserService service;

    @Autowired
    @Lazy
    private MenuService menuService;

    @Autowired
    @Lazy
    private RoleService roleService;

    /**
     * @param principalCollection
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @Description 授权
     * @Author ratel
     * @Date 2020-05-22
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //当前登录用户
        User user = (User) principalCollection.getPrimaryPrincipal();
        //权限

        List<Menu> menuList = menuService.list(user);
        Set<String> permissionSet = menuList.stream()
                .filter(menu -> menu != null && !StringUtils.isEmpty(menu.getPermissionCode()))
                .map(Menu::getPermissionCode).collect(Collectors.toSet());

        //角色
        List<Role> roleList = roleService.list(user);
        Set<String> roleCodeSet = roleList.stream().map(Role::getRoleCode).collect(Collectors.toSet());
        SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
        authenticationInfo.addRoles(roleCodeSet);
        authenticationInfo.addStringPermissions(permissionSet);
        log.info("当前用户拥有角色【{}】", roleCodeSet.toString());
        log.info("当前用户拥有权限【{}】", permissionSet.toString());
        return authenticationInfo;
    }

    /**
     * @param authenticationToken
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @Description 认证
     * @Author ratel
     * @Date 2020-05-22
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = (String) authenticationToken.getCredentials();
        User user = service.getByUsername(username);
        Assert.notNull(user, AUTH1005.getMsg());
        if (!user.getPassword().equals(password)) {
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
}
