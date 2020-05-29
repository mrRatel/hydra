package com.ratel.hydra.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.session.HttpServletSession;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ratel
 * @date 2020-05-24
 */
public class WebUtil {

    private static final String REDIRECT = "redirect:%s";

    public static String getRedirectUrl(String url) {
        return String.format(REDIRECT, url);
    }

    public static boolean isWebObject(Object obj) {
        if (obj instanceof ServletRequest) {
            return true;
        }
        if (obj instanceof ServletResponse) {
            return true;
        }
        if (obj instanceof HttpServletSession) {
            return true;
        }
        return false;
    }


    public static String getOperatingSystem(HttpServletRequest request) {
        StringBuilder userAgent = new StringBuilder("[");
        userAgent.append(request.getHeader("User-Agent"));
        userAgent.append("]");
        int indexOfMac = userAgent.indexOf("Mac OS X");
        int indexOfWindows = userAgent.indexOf("Windows NT");
        boolean isMac = indexOfMac > 0;
        boolean isWindows = indexOfWindows > 0;
        boolean isLinux = userAgent.indexOf("Linux") > 0;
        String os = "";
        if (isMac) {
            os = userAgent.substring(indexOfMac, indexOfMac + "MacOS X xxxxxxxx".length());
        } else if (isLinux) {
            os = "Linux";
        } else if (isWindows) {
            os = "Windows ";
            String version = userAgent.substring(indexOfWindows + "Windows NT".length(), indexOfWindows
                    + "Windows NTx.x".length());
            switch (version.trim()) {
                case "5.0":
                    os += "2000";
                    break;
                case "5.1":
                    os += "XP";
                    break;
                case "5.2":
                    os += "2003";
                    break;
                case "6.0":
                    os += "Vista";
                    break;
                case "6.1":
                    os += "7";
                    break;
                case "6.2":
                    os += "8";
                    break;
                case "6.3":
                    os += "8.1";
                    break;
                case "10":
                    os += "10";
                    break;
            }
        }
        return os;
    }

    public static String getBrowers(HttpServletRequest request) {
        StringBuilder userAgent = new StringBuilder("[");
        userAgent.append(request.getHeader("User-Agent"));
        userAgent.append("]");
        int indexOfIE = userAgent.indexOf("MSIE");
        int indexOfIE11 = userAgent.indexOf("rv:");
        int indexOfFF = userAgent.indexOf("Firefox");
        int indexOfSogou = userAgent.indexOf("MetaSr");
        int indexOfChrome = userAgent.indexOf("Chrome");
        int indexOfSafari = userAgent.indexOf("Safari");
        boolean isWindows = userAgent.indexOf("Windows NT") > 0;
        boolean containIE = indexOfIE > 0 || (isWindows && (indexOfIE11 > 0));
        boolean containFF = indexOfFF > 0;
        boolean containSogou = indexOfSogou > 0;
        boolean containChrome = indexOfChrome > 0;
        boolean containSafari = indexOfSafari > 0;
        String browser = "";
        if (containSogou) {
            if (containIE) {
                browser = "搜狗" + userAgent.substring(indexOfIE, indexOfIE + "IE x.x".length());
            } else if (containChrome) {
                browser = "搜狗" + userAgent.substring(indexOfChrome, indexOfChrome + "Chrome/xx".length());
            }
        } else if (containChrome) {
            browser = userAgent.substring(indexOfChrome, indexOfChrome + "Chrome/xx".length());
        } else if (containSafari) {
            int indexOfSafariVersion = userAgent.indexOf("Version");
            browser = "Safari "
                    + userAgent.substring(indexOfSafariVersion, indexOfSafariVersion + "Version/x.x.x.x".length());
        } else if (containFF) {
            browser = userAgent.substring(indexOfFF, indexOfFF + "Firefox/xx".length());
        } else if (containIE) {
            if (indexOfIE11 > 0) {
                browser = "IE 11";
            } else {
                browser = userAgent.substring(indexOfIE, indexOfIE + "IE x.x".length());
            }
        }
        return StringUtils.replace(browser, "/", " ");
    }

    public static String getReferer(HttpServletRequest request){
        return request.getHeader("Referer");
    }
}
