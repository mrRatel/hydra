package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.common.mapstruct.MenuTreeStruct;
import com.ratel.hydra.system.dto.MenuTree;
import com.ratel.hydra.system.mapper.MenuMapper;
import com.ratel.hydra.system.po.Menu;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuTreeStruct menuTreeStruct;

    @Override
    public List<Menu> list(User user) {
        return baseMapper.findCurrentUserMenu(user.getId());
    }

    @Override
    public List<Menu> list() {
        return super.list();
    }

    @Override
    public boolean saveOrUpdate(Menu entity, Wrapper<Menu> updateWrapper) {
        return false;
    }

    @Override
    public List<MenuTree> findMenuTreeList(User user) {
        List<Menu> currentUserMenu = baseMapper.findCurrentUserMenu(user.getId());
        Map<Long, MenuTree> map = new HashMap<>();
        currentUserMenu.forEach(a -> map.put(a.getId(), menuTreeStruct.toMenuTree(a)));
        List<MenuTree> list = new ArrayList<>();
        currentUserMenu.forEach(a -> {
            if (a.getParentId() == 0) {
                list.add(map.get(a.getId()));
                return;
            }
            MenuTree menuTree = map.get(a.getParentId());
            List<MenuTree> child = menuTree.getChildren();
            if (child == null) {
                child = new ArrayList<>();
                menuTree.setChildren(child);
            }
            child.add(map.get(a.getId()));
        });
        return list;
    }

    @Override
    public void batchInsert(List<MenuTree> list, Long pid) {
        list.forEach(a -> {
            Menu menu = menuTreeStruct.toMenu(a);
            menu.setParentId(pid);
            menu.setRoleId(1L);
            menu.setCreator(1L);
            menu.setModifier(1L);
            menu.setType(a.getType());
            //pid
            save(menu);
            List<MenuTree> child = a.getChildren();
            if (CollectionUtils.isEmpty(child)) {
                return;
            }
            batchInsert(child, menu.getId());
        });
    }

    @Override
    public IPage<Menu> findMenuListByPage(User currentUser) {
        IPage<Menu> page = new Page<>();
        List<Menu> currentUserMenu = baseMapper.findCurrentUserMenu(currentUser.getId());
        return page.setRecords(currentUserMenu).setTotal(currentUserMenu.size());
    }

    @Override
    public Menu findById(Long id) {

        return getById(id);
    }

    @Override
    public void delById(Long id) {
        delById(id);
    }

    @Override
    public void addOrUpdate(Menu menu) {
        saveOrUpdate(menu);
    }
}