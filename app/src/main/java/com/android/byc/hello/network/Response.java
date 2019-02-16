package com.android.byc.hello.network;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/15 13:06
 * @description
 */
public class Response<T> {
    public int ret;//约定  -404为server返回数据异常  其它为正常范围
    public String msg;
    public T data;


    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
