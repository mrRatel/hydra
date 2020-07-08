package com.ratel.hydra.common.mapstruct;

import com.ratel.hydra.system.dto.MenuTree;
import com.ratel.hydra.system.po.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ratel
 * @date 2020-06-02
 */
@Mapper(componentModel = "spring")
public interface MenuTreeStruct {

    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "href",source = "url"),
            @Mapping(target = "icon",source = "icon"),
            @Mapping(target = "pid",source = "parentId"),
            @Mapping(target = "title",source = "menuName"),
            @Mapping(target = "sort",source = "sort"),
            @Mapping(target = "type",source = "type"),
            @Mapping(target = "permissionCode",source = "permissionCode"),
    })
    MenuTree toMenuTree(Menu menu);

    @Mappings({
            @Mapping(target = "icon",source = "icon"),
            @Mapping(target = "menuName",source = "title"),
            @Mapping(target = "url",source = "href"),
            @Mapping(target = "id",source = "id"),
    })
    Menu toMenu(MenuTree menuTree);


    default List<MenuTree> toMenuTree(List<Menu> menus){
        List<Menu> currentUserMenu = menus;
        Map<Long, MenuTree> map = new HashMap<>();
        currentUserMenu.forEach(a -> map.put(a.getId(), this.toMenuTree(a)));
        List<MenuTree> list = new ArrayList<>();
        currentUserMenu.forEach(a -> {
            if (a.getParentId() == 0) {
                list.add(map.get(a.getId()));
                return;
            }
            MenuTree menuTree = map.get(a.getParentId());
            if (menuTree == null){
                System.out.println();
            }
            List<MenuTree> child = menuTree.getChildren();
            if (child == null) {
                child = new ArrayList<>();
                menuTree.setChildren(child);
            }
            child.add(map.get(a.getId()));
        });
        return list;
    }
}
