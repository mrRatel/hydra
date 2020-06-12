package com.ratel.hydra.system.test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ratel
 * @date 2020-06-12
 *  ServiceImpl<M extends BaseMapper<T>, T> implements IService<T>
 *
 *      BaseController<T extends IBaseServiceImpl<E>,E>
 *          interface IBaseServiceImpl<T>
 */

public class DDD <T extends TestInterface<E>,E> {
    @Autowired
    private T t;
}
