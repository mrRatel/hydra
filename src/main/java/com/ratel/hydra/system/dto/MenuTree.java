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
public class MenuTree{
    private String title;
    private Long id;
    private String icon;
    private String href;
    private Byte type;
    private List<MenuTree> children;
}
