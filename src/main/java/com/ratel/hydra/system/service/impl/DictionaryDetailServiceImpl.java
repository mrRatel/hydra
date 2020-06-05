package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.system.mapper.DictionaryDetailMapper;
import com.ratel.hydra.system.mapper.DictionaryMapper;
import com.ratel.hydra.system.po.Dictionary;
import com.ratel.hydra.system.po.DictionaryDetail;
import com.ratel.hydra.system.service.DictionaryDetailService;
import org.springframework.stereotype.Service;

/**
 * @author ratel
 * @date 2020-06-05
 */
@Service
public class DictionaryDetailServiceImpl extends ServiceImpl<DictionaryDetailMapper, DictionaryDetail> implements DictionaryDetailService {

    @Override
    public IPage<DictionaryDetail> getDetailByPid(IPage<DictionaryDetail> page, Long pId) {
        return page(page, new LambdaQueryWrapper<DictionaryDetail>().eq(DictionaryDetail::getPid, pId));
    }

    @Override
    public void addOrUpdate(DictionaryDetail detail) {
        saveOrUpdate(detail);
    }

    @Override
    public void delById(Long id) {
        removeById(id);
    }
}
