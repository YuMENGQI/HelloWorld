package com.android.byc.hello.util;

import android.content.Context;

import io.reactivex.functions.Consumer;


/**
 * @author yu
 * @version 1.0
 * @date 2019/2/16 9:26
 * @description
 */
public class Strategy {
    /**
     * 去观看，去分享，去添加 等按钮文字
     */
    private CharSequence buttonTitle;
    /**
     * 点击按钮后的行为
     */
    private Consumer<Context> consumer;

    public Strategy(CharSequence buttonTitle, Consumer<Context> consumer) {
        this.buttonTitle = buttonTitle;
        this.consumer = consumer;
    }

    public CharSequence getButtonTitle() {
        return buttonTitle;
    }

    /**
     * 执行
     * @param context context
     */
    public void act(Context context) {
        try {
            consumer.accept(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
