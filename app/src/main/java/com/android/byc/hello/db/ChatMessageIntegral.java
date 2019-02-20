package com.android.byc.hello.db;

import com.android.byc.hello.presenter.UUID2BytesConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

import java.util.UUID;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/19 8:50
 * @description
 */
@Entity(nameInDb = "ChatCurrencies")
public class ChatMessageIntegral {
    /**
     * 收入
     */
    public static final int TYPE_INCOME = 1;
    /**
     * 支出
     */
    public static final int TYPE_EXPEND = 2;


    /**
     * pk键
     */
    @Property(nameInDb = "PKChatCurrency")
    @Convert(converter = UUID2BytesConverter.class, columnType = byte[].class)
    public UUID PKChatCurrency;


    /**
     * 金额
     */
    @Property(nameInDb = "Amount")
    public int Amount;
    /**
     * 类型
     */
    @Property(nameInDb = "Type")
    public int Type;
    /**
     * 描述
     */
    @Property(nameInDb = "Description")
    public String Description;
    @Property(nameInDb = "CreateTime")
    public String CreateTime;

    @Generated(hash = 1890371283)
    public ChatMessageIntegral(UUID PKChatCurrency, int Amount, int Type,
                               String Description, String CreateTime) {
        this.PKChatCurrency = PKChatCurrency;
        this.Amount = Amount;
        this.Type = Type;
        this.Description = Description;
        this.CreateTime = CreateTime;
    }

    @Generated(hash = 339223182)
    public ChatMessageIntegral() {
    }

    public UUID getPKChatCurrency() {
        return this.PKChatCurrency;
    }

    public void setPKChatCurrency(UUID PKChatCurrency) {
        this.PKChatCurrency = PKChatCurrency;
    }

    public int getAmount() {
        return this.Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public int getType() {
        return this.Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getCreateTime() {
        return this.CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

}
