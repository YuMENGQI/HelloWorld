package com.android.byc.hello.db;

import com.android.byc.hello.presenter.UUID2BytesConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.util.UUID;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/18 17:27
 * @description
 */
@Entity(
        nameInDb = "CurrencyRecords",
        indexes = {
                @Index(value = "pkUser, createTime DESC", unique = true)
        }
)
public class CurrencyRecordsEntity {
    /***/
    @Id
    public Long id;
    /***/
    @NotNull
    @Property(nameInDb = "PKUser")
    @Convert(converter = UUID2BytesConverter.class, columnType = byte[].class)
    public UUID pkUser;
    /***/
    @Property(nameInDb = "PKCompany")
    @Convert(converter = UUID2BytesConverter.class, columnType = byte[].class)
    public UUID pkCompany;
    /***/
    @Property(nameInDb = "CurrencyTaskID")
    public Long currencyTaskId;
    /***/
    @Property(nameInDb = "Description")
    public String description = "";
    /** 1:收入  2：支出 */
    @Property(nameInDb = "Type")
    public int type = 1;
    /***/
    @Property(nameInDb = "Amount")
    public int amount;
    /***/
    @NotNull
    @Property(nameInDb = "CreateTime")
    public String createTime = "";
    @Generated(hash = 1913666918)
    public CurrencyRecordsEntity(Long id, @NotNull UUID pkUser, UUID pkCompany,
                                 Long currencyTaskId, String description, int type, int amount,
                                 @NotNull String createTime) {
        this.id = id;
        this.pkUser = pkUser;
        this.pkCompany = pkCompany;
        this.currencyTaskId = currencyTaskId;
        this.description = description;
        this.type = type;
        this.amount = amount;
        this.createTime = createTime;
    }
    @Generated(hash = 1198382507)
    public CurrencyRecordsEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public UUID getPkUser() {
        return this.pkUser;
    }
    public void setPkUser(UUID pkUser) {
        this.pkUser = pkUser;
    }
    public UUID getPkCompany() {
        return this.pkCompany;
    }
    public void setPkCompany(UUID pkCompany) {
        this.pkCompany = pkCompany;
    }
    public Long getCurrencyTaskId() {
        return this.currencyTaskId;
    }
    public void setCurrencyTaskId(Long currencyTaskId) {
        this.currencyTaskId = currencyTaskId;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getAmount() {
        return this.amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
