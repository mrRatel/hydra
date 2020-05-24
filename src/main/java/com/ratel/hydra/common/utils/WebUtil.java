package com.ratel.hydra.common.utils;

/**
 * @author ratel
 * @date 2020-05-24
 */
public class WebUtil {

    private static final String REDIRECT = "redirect:%s";

    public static String getRedirectUrl(String url){
        return String.format(REDIRECT,url);
    }
}
