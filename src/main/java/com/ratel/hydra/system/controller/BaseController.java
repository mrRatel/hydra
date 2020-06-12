package com.ratel.hydra.system.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ratel.hydra.common.constant.ExceptionEnum;
import com.ratel.hydra.common.execption.SystemException;
import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.vo.WebResult;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.service.impl.IBaseServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ratel
 * @date 2020-05-25
 */
//public class BaseController<T extends IBaseServiceImpl<BaseMapper,E>,BaseMapper,E>{
public class BaseController<T extends IBaseServiceImpl>{
    @Autowired
    protected T service;

    protected User currentUser() {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        if (principal == null) {
            throw new SystemException(ExceptionEnum.AUTH1008);
        }
        return principal;
    }

    @GetMapping("get/{id}")
    public WebResult getById(@PathVariable("id") Long id) {
        return WebResultFactory.ok(service.baseGetById(id));
    }

    @PostMapping("del/{id}")
    public WebResult delById(@PathVariable("id") Long id) {
        service.baseDelById(id);
        return WebResultFactory.ok();
    }

    @PostMapping("addOrUpdate")
    public WebResult addOrUpdate(@RequestBody E po) {
        service.baseAddOrUpdate(po);
        return WebResultFactory.ok(null, "操作成功");
    }

    @PostMapping("batchDel")
    public WebResult batchDel(@RequestBody List<Long> ids) {
        service.batchDelByIds(ids);
        return WebResultFactory.ok(null, "操作成功");
    }


    @GetMapping("page")
    public WebResult page(PageQuery<E> query, E obj) {
        return WebResultFactory.ok(service.basePage(query.setQuery(obj)));
    }
}
