package com.ratel.hydra.system.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author ratel
 * @date 2020-06-09
 */
@Data
@Accessors(chain = true)
public class PageQuery<E> {
    private E query;
    private Page<E> page = new Page();
}
