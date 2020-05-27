package com.ratel.hydra.system.dto;

import com.ratel.hydra.system.po.Menu;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ratel
 * @date 2020-05-25
 */
@Data
public class MenuTree extends ArrayList<MenuTree> {
    private Long pid;
    private Menu menu;
    private List<MenuTree> list ;

    public void addMenuTree(MenuTree menuTree){
        if (list == null){
            list = new ArrayList<>();
        }
        list.add(menuTree);
    }
}
