package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.system.mapper.MenuMapper;
import com.ratel.hydra.system.po.Menu;
import com.ratel.hydra.system.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ratel
 * @date 2020-05-21
 */
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
}
