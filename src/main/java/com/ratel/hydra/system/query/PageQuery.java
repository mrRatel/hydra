package com.ratel.hydra.system.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * @author ratel
 * @date 2020-06-09
 */
@Data
@Accessors(chain = true)
public class PageQuery<E> implements Serializable {
    private E query;
    private Page<E> page = new Page();
}
