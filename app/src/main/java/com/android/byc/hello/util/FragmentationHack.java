package com.android.byc.hello.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import java.util.List;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/16 13:42
 * @description
 */
class FragmentationHack {
    public static List<Fragment> getActiveFragments(FragmentManager fragmentManager) {
        return fragmentManager.getFragments();
    }
}
