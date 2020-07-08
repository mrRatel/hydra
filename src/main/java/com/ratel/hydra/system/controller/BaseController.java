package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.annotation.OperatingInfo;
import com.ratel.hydra.common.constant.ExceptionEnum;
import com.ratel.hydra.common.execption.SystemException;
import com.ratel.hydra.common.factory.WebResultFactory;
import com.ratel.hydra.common.pojo.WebResult;
import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.query.PageQuery;
import com.ratel.hydra.system.service.impl.IBaseServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author ratel
 * @date 2020-05-25
 */
public class BaseController<E extends IBaseServiceImpl, T> {

    protected E iBaseService;

    protected User currentUser() {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        if (principal == null) {
            throw new SystemException(ExceptionEnum.AUTH1008);
        }
        return principal;
    }

    @GetMapping("get/{id}")
//    @OperatingInfo(operation = "获取")
    public WebResult getById(@PathVariable("id") Long id) {
        return WebResultFactory.ok(iBaseService.baseGetById(id));
    }

    @PostMapping("del/{id}")
    @OperatingInfo(operation = "删除")
    public WebResult delById(@PathVariable("id") Long id) {
        iBaseService.baseDelById(id);
        return WebResultFactory.ok();
    }

    @PostMapping("addOrUpdate")
    @OperatingInfo(operation = "保存")
    public WebResult addOrUpdate(@RequestBody T po) {

        iBaseService.baseAddOrUpdate(po);
        return WebResultFactory.ok(null, "操作成功");
    }

    @PostMapping("batchDel")
    @OperatingInfo(operation = "批量删除")
    public WebResult batchDel(@RequestBody List<Long> ids) {
        iBaseService.batchDelByIds(ids);
        return WebResultFactory.ok(null, "操作成功");
    }


    @GetMapping("page")
//    @OperatingInfo(operation = "分页获取")
    public WebResult page(PageQuery<T> query, T po) {
        return WebResultFactory.ok(iBaseService.basePage(query.setQuery(po)));
    }

    @Autowired
    private void registionService(List<IBaseServiceImpl> iBaseServices) {
        Type t = this.getClass().getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            Type actualTypeArgument = ((ParameterizedType) t).getActualTypeArguments()[0];
            for (IBaseServiceImpl baseService : iBaseServices) {
                if (AopUtils.getTargetClass(baseService).getName().equals(actualTypeArgument.getTypeName())) {
                    iBaseService = (E) baseService;
                    return;
                }
            }
        }
    }
}
