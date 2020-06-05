package com.ratel.hydra.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ratel.hydra.system.po.Dictionary;
import com.ratel.hydra.system.po.DictionaryDetail;

import java.util.List;

public interface DictionaryService {

   List<Dictionary> list();

    void delById(Long id);

    void addOrUpdate(Dictionary dictionary);
}
