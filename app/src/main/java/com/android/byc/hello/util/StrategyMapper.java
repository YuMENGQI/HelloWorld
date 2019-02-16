package com.android.byc.hello.util;

import android.content.Context;
import android.content.Intent;

import com.android.byc.hello.view.SecondActivity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/16 9:33
 * @description
 */
public class StrategyMapper {

    /** 观看梵讯大学 */
    public static final String WATCH_FOOWW_COLLEGE = "1";
    /** 分享梵讯大学 */
    public static final String SHARE_FOOWW_COLLEGE = "2";
    /** 添加朋友 */
    public static final String ADD_FRIENDS = "3";
    /** 邀请注册 */
    public static final String INVITE_REGISTE = "4";
    /** 邀请开通 */
    public static final String INVITE_OPEN = "5";

    /** 按钮点击行为匹配表 */
    private static final Map<String, Strategy> STRATEGY_MAP = new HashMap<>();

    static {
        // 去梵讯大学
        Consumer<Context> goFoowwCollege = new Consumer<Context>() {
            @Override
            public void accept(Context context) throws Exception {
                Intent intentCollege = new Intent(context, SecondActivity.class);
                intentCollege.putExtra("fromActivity", "MyCoinsActivity");
                context.startActivity(intentCollege);
            }
        };
        // 添加好友
        Consumer<Context> goFriends = new Consumer<Context>() {
            @Override
            public void accept(Context context) throws Exception {
                Intent intentFriends = new Intent(context, SecondActivity.class);
                context.startActivity(intentFriends);
            }
        };
        STRATEGY_MAP.put(WATCH_FOOWW_COLLEGE, new Strategy("去观看", goFoowwCollege));
        STRATEGY_MAP.put(SHARE_FOOWW_COLLEGE, new Strategy("去分享", goFoowwCollege));
        STRATEGY_MAP.put(ADD_FRIENDS, new Strategy("去添加", goFriends));
        STRATEGY_MAP.put(INVITE_REGISTE, new Strategy("去邀请", goFriends));
        STRATEGY_MAP.put(INVITE_OPEN, new Strategy("去邀请", goFriends));
    }

    /**
     *
     * @param pkTask PKCurrencyTask
     * @return 包含按钮标题和点击行为的策略
     */
    public static Strategy getStrategy(String pkTask) {
        return STRATEGY_MAP.get(pkTask);
    }
}
