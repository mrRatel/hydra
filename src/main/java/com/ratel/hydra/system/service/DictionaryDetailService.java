package com.ratel.hydra.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ratel.hydra.system.po.DictionaryDetail;

public interface DictionaryDetailService {

    IPage<DictionaryDetail> getDetailByPid(IPage<DictionaryDetail> page, Long pId);

    void addOrUpdate(DictionaryDetail detail);

    void delById(Long id);
}
