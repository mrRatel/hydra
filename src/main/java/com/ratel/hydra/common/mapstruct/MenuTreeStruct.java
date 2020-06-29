package com.ratel.hydra.common.mapstruct;

import com.ratel.hydra.system.dto.MenuTree;
import com.ratel.hydra.system.po.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

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
    })
    MenuTree toMenuTree(Menu menu);

    @Mappings({
            @Mapping(target = "icon",source = "icon"),
            @Mapping(target = "menuName",source = "title"),
            @Mapping(target = "url",source = "href"),
            @Mapping(target = "id",source = "id"),
    })
    Menu toMenu(MenuTree menuTree);
}
