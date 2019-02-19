package com.android.byc.hello.network;

import java.io.Serializable;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/18 11:20
 * @description
 */
public class HttpResponse implements Serializable {
    @EntityIgnore
    public int ret;

    @EntityIgnore
    public String msg;

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
}
