package com.ratel.hydra.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.system.mapper.DictionaryDetailMapper;
import com.ratel.hydra.system.mapper.DictionaryMapper;
import com.ratel.hydra.system.po.Dictionary;
import com.ratel.hydra.system.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ratel
 * @date 2020-06-05
 */
@Slf4j
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper,Dictionary> implements DictionaryService {
    @Autowired
    private DictionaryDetailMapper detailMapper;

    @Override
    public List<Dictionary> list() {
        return super.list();
    }

    @Override
    public void delById(Long id) {
       removeById(id);
    }

    @Override
    public void addOrUpdate(Dictionary dictionary) {
        saveOrUpdate(dictionary);
    }

}
