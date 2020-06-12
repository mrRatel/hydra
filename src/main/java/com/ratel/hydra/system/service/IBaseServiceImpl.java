package com.ratel.hydra.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ratel.hydra.system.query.PageQuery;

import java.util.List;

/**
 * @author ratel
 * @date 2020-06-12
 */
//public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T> {
public interface IBaseServiceImpl<T>  {

     T baseGetById(Long id);

     void baseDelById(Long id);

     void baseAddOrUpdate(T po);

     IPage<T> basePage(PageQuery<T> query);

    void batchDelByIds(List<Long> ids);

}
