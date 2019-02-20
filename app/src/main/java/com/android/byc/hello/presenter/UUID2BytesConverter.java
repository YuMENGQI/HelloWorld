package com.android.byc.hello.presenter;

import com.android.byc.hello.util.Utility;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.UUID;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/19 15:15
 * @description
 */
 // 实现UUID在数据库中Byte值的转化
public class UUID2BytesConverter implements PropertyConverter<UUID, byte[]> {
    @Override
    public UUID convertToEntityProperty(byte[] databaseValue) {
        return Utility.ConvertBytesToUUID(databaseValue);
    }

    @Override
    public byte[] convertToDatabaseValue(UUID entityProperty) {
        return new byte[0];
    }
}
