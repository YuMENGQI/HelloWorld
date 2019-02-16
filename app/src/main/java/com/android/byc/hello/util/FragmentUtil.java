package com.android.byc.hello.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/16 13:40
 * @description
 */
public class FragmentUtil {
    private static volatile FragmentUtil sInstance;

    private FragmentUtil (){}

    public static FragmentUtil getInstance(){
        if (sInstance == null){
            synchronized (FragmentUtil.class){
                if (sInstance == null){
                    sInstance = new FragmentUtil();
                }
            }
        }
        return sInstance;
    }

    public <T extends Fragment> T findStackFragment(Class<T> fragmentClass, String toFragmentTag, FragmentManager
            fragmentManager) {
        Fragment fragment = null;

        if (toFragmentTag == null) {
            List<Fragment> fragmentList = FragmentationHack.getActiveFragments(fragmentManager);
            if (fragmentList == null) {
                return null;
            }

            int sizeChildFrgList = fragmentList.size();

            for (int i = sizeChildFrgList - 1; i >= 0; i--) {
                Fragment brotherFragment = fragmentList.get(i);
                if (brotherFragment.getClass().getName().equals(fragmentClass.getName())) {
                    fragment = brotherFragment;
                    break;
                }
            }
        } else {
            fragment = fragmentManager.findFragmentByTag(toFragmentTag);
            if (fragment == null) {
                return null;
            }
        }
        return (T) fragment;
    }
}
