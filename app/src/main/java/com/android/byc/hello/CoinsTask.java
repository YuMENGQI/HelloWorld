package com.android.byc.hello;

import android.widget.TextView;

/**
 * @author yu
 * @version 1.0
 * @date 2019/1/8 16:14
 * @description
 */
public class  CoinsTask {
    public int score;
    public String title;
    public String content;
    public String action;

    public CoinsTask(int score, String title, String content, String action) {
        this.score = score;
        this.title = title;
        this.content = content;
        this.action = action;
    }
}
