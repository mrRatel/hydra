package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.common.mapstruct.MenuTreeStruct;
import com.ratel.hydra.common.utils.ConvertUtils;
import com.ratel.hydra.system.dto.LayuiTree;
import com.ratel.hydra.system.dto.MenuTree;
import com.ratel.hydra.system.mapper.MenuMapper;
import com.ratel.hydra.system.po.Menu;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
            List<MenuTree> child = menuTree.getChild();
            if (child == null) {
                child = new ArrayList<>();
                menuTree.setChild(child);
            }
            child.add(map.get(a.getId()));
        });
        return list;
    }
}