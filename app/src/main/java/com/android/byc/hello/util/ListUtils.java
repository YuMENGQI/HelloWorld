package com.android.byc.hello.util;

import com.android.byc.hello.db.UserModelEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/18 13:37
 * @description
 */
public class ListUtils {
    /**
     * 对 List 过滤并排序, 降序
     * @param userModels list
     * @return list
     */
    public static List<UserModelEntity> filterThenSort(List<UserModelEntity> userModels) {
        List<UserModelEntity> list = new ArrayList<>();
        if (userModels == null || userModels.size() == 0) {
            return list;
        }

        for (UserModelEntity entity : userModels) {
            if (entity.getCurrencyAmount() != 0) {
                list.add(entity);
            }
        }
        Collections.sort(list, new Comparator<UserModelEntity>() {
            @Override
            public int compare(UserModelEntity o1, UserModelEntity o2) {
                return o2.getCurrencyAmount() - o1.getCurrencyAmount();
            }
        });
        return list;
    }
}
