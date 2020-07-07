package com.ratel.hydra.common.pojo;

import lombok.Data;

/**
 * @author ratel
 * @date 2020/7/5
 */
@Data
public class KVBean<K,V> {
    private K k;
    private V v;
}
