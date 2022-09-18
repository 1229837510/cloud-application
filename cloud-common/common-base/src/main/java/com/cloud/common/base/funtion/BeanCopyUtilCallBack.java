package com.cloud.common.base.funtion;

/**
 * @author fangcy
 * @date 2022-09-18
 */
@FunctionalInterface
public interface BeanCopyUtilCallBack<S,T> {
    /**
     * 定义默认回调方法
     * @param t
     * @param s
     */
    void callBack(S t, T s);
}
