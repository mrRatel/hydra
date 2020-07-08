package com.ratel.hydra.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ratel.hydra.system.po.Menu;import com.ratel.hydra.system.vo.MenuVO;import org.apache.ibatis.annotations.Param;import java.util.List;

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
     * @param userId
     * @return java.util.List<com.ratel.hydra.system.vo.MenuVO>
     * @Description 获取所有菜单
     * @Author ratel
     * @Date 2020/7/5
     **/
    List<MenuVO> selectMenuVOS(@Param("userId") Long userId);
}