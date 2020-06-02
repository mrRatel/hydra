package com.ratel.hydra.system.dto;

import lombok.Data;

import java.util.List;

/**
 * @author ratel
 * @date 2020-05-31
 */
@Data
public class LayuiTree<T> {
    private String title;
    private Long id;
    private String filed;
    private boolean checked;
    private boolean spread;
    private T data;
    private List<LayuiTree<T>> children;
}
