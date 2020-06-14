package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.system.po.Role;
import com.ratel.hydra.system.query.PageQuery;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author ratel
 * @date 2020-06-12
 */
public class IBaseServiceImpl<M extends BaseMapper<T>,T> extends ServiceImpl<M,T> {

    public IPage<T> basePage(PageQuery<T> query) {
        return page(query.getPage());
    }

    public void baseAddOrUpdate(T po) {
        saveOrUpdate(po);
    }


    public void batchDelByIds(List<Long> ids) {
        removeByIds(ids);
    }

    public T baseGetById(Long id) {
        return getById(id);
    }

    public void baseDelById(Long id) {
        removeById(id);
    }
}
