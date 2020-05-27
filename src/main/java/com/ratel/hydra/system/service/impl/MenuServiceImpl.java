package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.system.dto.MenuTree;
import com.ratel.hydra.system.mapper.MenuMapper;
import com.ratel.hydra.system.po.Menu;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<Menu> list(Long pid, User user) {
        return baseMapper.findCurrentUserMenuByPid(pid == null ? 0 : pid, user.getId());
    }

    @Override
    public List<Menu> list() {
        return super.list();
    }

    @Override
    public List<MenuTree> findMenuTreeList(User user) {
        List<Menu> menuList = baseMapper.findCurrentUserMenuByPid(0L, user.getId());
        return buildMenuTreeList(menuList);
    }

    private List<MenuTree> buildMenuTreeList(List<Menu> menus) {
        List<MenuTree> menuTreeList = new ArrayList<>();
        menus.forEach(menu -> {
            MenuTree menuTree = buildMenu(menu);
            if (menu.getId() == 0) {
                menuTreeList.add(menuTree);
            }
            menuTreeList.forEach(item -> {
                if (Objects.equals(menu.getParentId(),item.getMenu().getId())) {
                    item.addMenuTree(menuTree);
                }
            });
        });
        return menuTreeList;
    }

    private MenuTree buildMenu(Menu menu) {
        MenuTree menuTree = new MenuTree();
        menuTree.setMenu(menu);
        return menuTree;
    }

}