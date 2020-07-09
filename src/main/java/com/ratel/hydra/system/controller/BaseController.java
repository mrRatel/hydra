package com.ratel.hydra.system.controller;

import com.ratel.hydra.common.constant.ExceptionEnum;
import com.ratel.hydra.common.execption.SystemException;
import com.ratel.hydra.system.po.User;
import org.apache.shiro.SecurityUtils;

/**
 * @author ratel
 * @date 2020-05-25
 */
public class BaseController{

    protected User currentUser() {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        if (principal == null) {
            throw new SystemException(ExceptionEnum.AUTH1008);
        }
        return principal;
    }
/*
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
    }*/

   /* @Autowired
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
    }*/
}
