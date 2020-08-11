package com.ratel.hydra.common.utils;

import com.ratel.hydra.common.constant.ExceptionEnum;
import com.ratel.hydra.common.execption.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ratel
 * @date 2020-05-31
 */
@Slf4j
public class ConvertUtils {


    /**
     * 将 list 转换成 指定字段的list
     * @param list
     * @param fieldName
     * @param clazz
     * @param <T>
     * @return
     * @throws
     */
    public  static <T> List<T> toFieldList(List list, String fieldName, Class<T> clazz){
        List<T> tempList = new ArrayList();
        for (Object o : list) {
            PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(o.getClass(), fieldName);
            Method readMethod = null;
            try {
                readMethod = propertyDescriptor.getReadMethod();
                Object invoke = readMethod.invoke(o);
                T cast = clazz.cast(invoke);
                tempList.add(cast);
            } catch (Exception e) {
                log.error("------------------------------反射 调用方法失败{}----------------------------",e);
                throw new BusinessException(ExceptionEnum.SYS1003,readMethod.getName());
            }
        }
        return tempList;
    }

    /**
     * 将list 中指定元素的字段 作为key，元素作为value 转换为map
     * @param list
     * @param fieldName
     * @param kClass
     * @param vClass
     * @param <T>
     * @param <V>
     * @return
     * @throws BusinessException
     */
    public  static <T,V> Map<T,V> toMap(List list, String fieldName, Class<T> kClass, Class<V> vClass){
        Map<T,V> map = new HashMap<>();
        for (Object o : list) {
            PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(o.getClass(), fieldName);
            Method readMethod = null;
            try {
                readMethod = propertyDescriptor.getReadMethod();
                Object invoke = readMethod.invoke(o);
                T cast = kClass.cast(invoke);
                V cast1 = vClass.cast(o);
                map.put(cast,cast1);
            } catch (Exception e) {
                log.error("------------------------------反射 调用方法失败{}----------------------------",e);
                throw new BusinessException(ExceptionEnum.SYS1003,readMethod.getName());
            }
        }
        return map;
    }

    /**
     * @Description 按指定字段分组
     * @Author      ratel
     * @Date        2020/4/9
     * @param       list
     * @param       fieldName
     * @param       kClass
     * @param       vClass
     * @return      java.util.Map<T,java.util.List<V>>
     **/
    public  static <T,V> Map<T,List<V>> toGroupMap(List list, String fieldName, Class<T> kClass,Class<V> vClass){
        Map<T,List<V>> map = new HashMap<>();
        for (Object o : list) {
            PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(o.getClass(), fieldName);
            Method readMethod = null;
            try {
                readMethod = propertyDescriptor.getReadMethod();
                Object invoke = readMethod.invoke(o);
                T k = kClass.cast(invoke);
                List<V> vList = map.get(k);
                if (vList == null){
                    vList = new ArrayList<>();
                    map.put(k,vList);
                }
                V v = vClass.cast(o);
                vList.add(v);
            } catch (Exception e) {
                log.error("------------------------------反射 调用方法失败{}----------------------------",e);
                throw new BusinessException(ExceptionEnum.SYS1003,readMethod.getName());
            }
        }
        return map;
    }
}
