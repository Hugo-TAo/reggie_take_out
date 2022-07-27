package com.itheima.reggie.comon;

/**
 * @author HuJiangTao
 * @create 2022/7/27 22:17
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
