package com.ratel.hydra.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ratel.hydra.system.dto.LayuiTree;
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
     * @param       user
     * @return      java.util.List<com.ratel.hydra.system.po.Menu>
     **/
    List<Menu> list(User user);

    List<Menu> list();

    List<MenuTree> findMenuTreeList(User user);

    void batchInsert(List<MenuTree> list,Long pid);

    Menu findById(Long id);

    void delById(Long id);

    void addOrUpdate(Menu menu);
}
