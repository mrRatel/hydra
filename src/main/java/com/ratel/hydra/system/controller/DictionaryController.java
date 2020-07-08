package com.ratel.hydra.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ratel.hydra.common.annotation.OperatingInfo;
import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.pojo.WebResult;
import com.ratel.hydra.system.po.Dictionary;
import com.ratel.hydra.system.po.DictionaryDetail;
import com.ratel.hydra.system.service.DictionaryDetailService;
import com.ratel.hydra.system.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ratel
 * @date 2020-06-05
 */
@Slf4j
@RestController
@RequestMapping("dictionary")
@OperatingInfo(tag = "字典")
public class DictionaryController {

    @Autowired
    private DictionaryService service;
    @Autowired
    private DictionaryDetailService detailService;

    @GetMapping("list")
    public WebResult list(){
        return WebResultFactory.ok(service.list());
    }

    @PostMapping("del/{id}")
    public WebResult del(@PathVariable("id") Long id){
        service.delById(id);
        return WebResultFactory.ok();
    }

    @GetMapping("detail/{pid}")
    public WebResult getDetailByPid(IPage<DictionaryDetail> page, @PathVariable("pid") Long pId){
        return WebResultFactory.ok(detailService.getDetailByPid(page,pId));
    }

    @PostMapping("addOrUpdate")
    public WebResult addOrUpdate(@RequestBody Dictionary dictionary){
        service.addOrUpdate(dictionary);
        return WebResultFactory.ok();
    }

    @PostMapping("detail/addOrUpdate")
    public WebResult addOrUpdateDetail(@RequestBody DictionaryDetail detail){
        detailService.addOrUpdate(detail);
        return WebResultFactory.ok();
    }

    @PostMapping("detail/del/{id}")
    public WebResult delDetail(@PathVariable("id") Long id){
        detailService.delById(id);
        return WebResultFactory.ok();
    }
}
