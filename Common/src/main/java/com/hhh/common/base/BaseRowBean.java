package com.hhh.common.base;

import java.io.Serializable;

/**
 * @Description:实体类的基类，先按照当前wanandroid接口api数据格式，后期可以换成其他的
 * @Author: hhh
 * @CreateDate: 2020/9/15
 */
public class BaseRowBean<T> implements Serializable {
    /**
     * data :
     * errorCode : 0
     * errorMsg :
     */

    public int code;
    public String msg;
    public T rows;

    public BaseRowBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
