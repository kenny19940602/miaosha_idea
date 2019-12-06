package com.imooc.kenny.access;

import com.imooc.kenny.model.User;

/**
 * ClassName: UserContext
 * Function:  把用户保存在ThreadLocal(线程安全的)中
 * Date:      2019/12/6 14:50
 * @author     Kenny
 * version    V1.0
 */
public class UserContext {

    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static void setUser(User user) {
        userThreadLocal.set(user);
    }

    public static User getUser() {
        return userThreadLocal.get();
    }
}
