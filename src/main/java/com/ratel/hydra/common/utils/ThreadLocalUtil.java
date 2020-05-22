package com.ratel.hydra.common.utils;

import com.ratel.hydra.system.po.User;

/**
 * @author ratel
 * @date 2020-05-21
 */
public class ThreadLocalUtil {

    private static final ThreadLocal<User> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void cleanUserInfo(){
        USER_THREAD_LOCAL.remove();
    }

    public static User getUserInfo(){
       return USER_THREAD_LOCAL.get();
    }

    public static void setUser(User user){
        USER_THREAD_LOCAL.set(user);
    }

}
