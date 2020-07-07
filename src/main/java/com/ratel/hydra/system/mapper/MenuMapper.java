package com.ratel.hydra.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ratel.hydra.system.po.Menu;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.vo.MenuVO;
import org.apache.ibatis.annotations.Param;import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * @param userId
     * @return java.util.List<com.ratel.hydra.system.po.Menu>
     * @Description 通过用户id 和 pid 获取当前用户的菜单
     * @Author ratel
     * @Date 2020-05-25
     */
    List<Menu> findCurrentUserMenu(@Param("userId") Long userId);

    /**
     * @Description 获取所有菜单
     * @Author      ratel
     * @Date        2020/7/5
     * @param       currentUser
     * @return      java.util.List<com.ratel.hydra.system.vo.MenuVO>
     **/
    List<MenuVO> selectMenuVOS(User currentUser);
}