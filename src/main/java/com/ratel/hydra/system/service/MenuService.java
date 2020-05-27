package com.ratel.hydra.system.service;

import com.ratel.hydra.system.dto.MenuTree;
import com.ratel.hydra.system.po.Menu;
import com.ratel.hydra.system.po.User;

import java.util.List;

/**
 * @author ratel
 * @date 2020-05-21
 */
public interface MenuService {

    /**
     * @Description 通过Pid 和 用户id 获取当前用户所能看到的菜单
     * @Author      ratel
     * @Date        2020-05-25
     * @param       pid
     * @param       user
     * @return      java.util.List<com.ratel.hydra.system.po.Menu>
     **/
    List<Menu> list(Long pid, User user);

    List<Menu> list();

    List<MenuTree> findMenuTreeList(User user);
}
