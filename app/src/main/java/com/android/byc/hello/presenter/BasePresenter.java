package com.android.byc.hello.presenter;

import android.content.Context;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/16 13:09
 * @description
 */
public abstract class BasePresenter <T>{
    public T view;
    public Context context;
    public DBManager dbManager;
}
