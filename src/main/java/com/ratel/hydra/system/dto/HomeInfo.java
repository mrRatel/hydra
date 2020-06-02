package com.ratel.hydra.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ratel
 * @date 2020-06-02
 */
@NoArgsConstructor
@Data
public class HomeInfo {

    /**
     * homeInfo : {"title":"首页","href":"page/welcome-1.html?t=1"}
     * logoInfo : {"title":"LAYUI MINI","image":"images/logo.png","href":""}
     * menuInfo : []
     */

    private HomeInfoBean homeInfo;
    private LogoInfoBean logoInfo;
    private List<MenuTree> menuInfo;

    @NoArgsConstructor
    @Data
    public static class HomeInfoBean {
        /**
         * title : 首页
         * href : page/welcome-1.html?t=1
         */

        private String title;
        private String href;
    }

    @NoArgsConstructor
    @Data
    public static class LogoInfoBean {
        /**
         * title : LAYUI MINI
         * image : images/logo.png
         * href :
         */

        private String title;
        private String image;
        private String href;
    }
}
