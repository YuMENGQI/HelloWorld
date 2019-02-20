package com.android.byc.hello.db;

import com.android.byc.hello.network.EntityIgnore;
import com.android.byc.hello.network.HttpResponse;
import com.android.byc.hello.presenter.UUID2BytesConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

import java.util.UUID;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/18 11:36
 * @description
 */
@Entity(nameInDb = "UserModel")
public class UserModelEntity extends HttpResponse {
    @Property(nameInDb = "PKUser")
    @Convert(converter = UUID2BytesConverter.class, columnType = byte[].class)
    public UUID PKUser;

    @Property(nameInDb = "PKCompanyGroup")
    @Convert(converter = UUID2BytesConverter.class, columnType = byte[].class)
    public UUID PKCompanyGroup;

    @Property(nameInDb = "PKCompanyDepartment")
    @Convert(converter = UUID2BytesConverter.class, columnType = byte[].class)
    public UUID PKCompanyDepartment;

    @Property(nameInDb = "PKCompanyRegion")
    @Convert(converter = UUID2BytesConverter.class, columnType = byte[].class)
    public UUID PKCompanyRegion;

    @Property(nameInDb = "PKCompany")
    @Convert(converter = UUID2BytesConverter.class, columnType = byte[].class)
    public UUID PKCompany;

    @Property(nameInDb = "PKFoowwRole")
    @Convert(converter = UUID2BytesConverter.class, columnType = byte[].class)
    public UUID PKFoowwRole;

    @Property(nameInDb = "Email")
    public String Email;

    @Property(nameInDb = "Cellphone")
    public String Cellphone;

    @Property(nameInDb = "ContactCellphone")
    public String ContactCellphone;

    @Property(nameInDb = "Telephone")
    public String Telephone;

    @Property(nameInDb = "QQ")
    public String QQ;

    @Property(nameInDb = "UserName")
    public String UserName;

    @Property(nameInDb = "GroupName")
    public String GroupName;

    @Property(nameInDb = "RegionName")
    public String RegionName;

    @Property(nameInDb = "RoleName")
    public String RoleName;

    @Property(nameInDb = "MobileEnabledStatus")
    public int MobileEnabledStatus;

    @Property(nameInDb = "Password")
    public String Password;

    @Property(nameInDb = "Salt")
    public String Salt;

    @Property(nameInDb = "EndTime")
    public String EndTime;

    @Property(nameInDb = "DepartmentName")
    public String DepartmentName;

    @Property(nameInDb = "DeleteStatus")
    public int DeleteStatus;

    @Property(nameInDb = "Enabled")
    public boolean Enabled;

    @Property(nameInDb = "PhotoPath")
    public String PhotoPath;
    @Property(nameInDb = "UserPinyin")
    public String UserPinyin;
    @Property(nameInDb = "UserPinyinAbbr")
    public String UserPinyinAbbr;
    @Property(nameInDb = "Title")
    public String Title;
    @Property(nameInDb = "CurrencyAmount")
    public int CurrencyAmount;

    @Property(nameInDb = "PrivateDealCount")
    public int PrivateDealCount;
    @EntityIgnore
    @Transient
    public int IsSysAdmin;
    @Generated(hash = 1493275079)
    public UserModelEntity(UUID PKUser, UUID PKCompanyGroup,
                           UUID PKCompanyDepartment, UUID PKCompanyRegion, UUID PKCompany,
                           UUID PKFoowwRole, String Email, String Cellphone,
                           String ContactCellphone, String Telephone, String QQ, String UserName,
                           String GroupName, String RegionName, String RoleName,
                           int MobileEnabledStatus, String Password, String Salt, String EndTime,
                           String DepartmentName, int DeleteStatus, boolean Enabled,
                           String PhotoPath, String UserPinyin, String UserPinyinAbbr,
                           String Title, int CurrencyAmount, int PrivateDealCount) {
        this.PKUser = PKUser;
        this.PKCompanyGroup = PKCompanyGroup;
        this.PKCompanyDepartment = PKCompanyDepartment;
        this.PKCompanyRegion = PKCompanyRegion;
        this.PKCompany = PKCompany;
        this.PKFoowwRole = PKFoowwRole;
        this.Email = Email;
        this.Cellphone = Cellphone;
        this.ContactCellphone = ContactCellphone;
        this.Telephone = Telephone;
        this.QQ = QQ;
        this.UserName = UserName;
        this.GroupName = GroupName;
        this.RegionName = RegionName;
        this.RoleName = RoleName;
        this.MobileEnabledStatus = MobileEnabledStatus;
        this.Password = Password;
        this.Salt = Salt;
        this.EndTime = EndTime;
        this.DepartmentName = DepartmentName;
        this.DeleteStatus = DeleteStatus;
        this.Enabled = Enabled;
        this.PhotoPath = PhotoPath;
        this.UserPinyin = UserPinyin;
        this.UserPinyinAbbr = UserPinyinAbbr;
        this.Title = Title;
        this.CurrencyAmount = CurrencyAmount;
        this.PrivateDealCount = PrivateDealCount;
    }
    @Generated(hash = 534024954)
    public UserModelEntity() {
    }
    public UUID getPKUser() {
        return this.PKUser;
    }
    public void setPKUser(UUID PKUser) {
        this.PKUser = PKUser;
    }
    public UUID getPKCompanyGroup() {
        return this.PKCompanyGroup;
    }
    public void setPKCompanyGroup(UUID PKCompanyGroup) {
        this.PKCompanyGroup = PKCompanyGroup;
    }
    public UUID getPKCompanyDepartment() {
        return this.PKCompanyDepartment;
    }
    public void setPKCompanyDepartment(UUID PKCompanyDepartment) {
        this.PKCompanyDepartment = PKCompanyDepartment;
    }
    public UUID getPKCompanyRegion() {
        return this.PKCompanyRegion;
    }
    public void setPKCompanyRegion(UUID PKCompanyRegion) {
        this.PKCompanyRegion = PKCompanyRegion;
    }
    public UUID getPKCompany() {
        return this.PKCompany;
    }
    public void setPKCompany(UUID PKCompany) {
        this.PKCompany = PKCompany;
    }
    public UUID getPKFoowwRole() {
        return this.PKFoowwRole;
    }
    public void setPKFoowwRole(UUID PKFoowwRole) {
        this.PKFoowwRole = PKFoowwRole;
    }
    public String getEmail() {
        return this.Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getCellphone() {
        return this.Cellphone;
    }
    public void setCellphone(String Cellphone) {
        this.Cellphone = Cellphone;
    }
    public String getContactCellphone() {
        return this.ContactCellphone;
    }
    public void setContactCellphone(String ContactCellphone) {
        this.ContactCellphone = ContactCellphone;
    }
    public String getTelephone() {
        return this.Telephone;
    }
    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }
    public String getQQ() {
        return this.QQ;
    }
    public void setQQ(String QQ) {
        this.QQ = QQ;
    }
    public String getUserName() {
        return this.UserName;
    }
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public String getGroupName() {
        return this.GroupName;
    }
    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }
    public String getRegionName() {
        return this.RegionName;
    }
    public void setRegionName(String RegionName) {
        this.RegionName = RegionName;
    }
    public String getRoleName() {
        return this.RoleName;
    }
    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }
    public int getMobileEnabledStatus() {
        return this.MobileEnabledStatus;
    }
    public void setMobileEnabledStatus(int MobileEnabledStatus) {
        this.MobileEnabledStatus = MobileEnabledStatus;
    }
    public String getPassword() {
        return this.Password;
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }
    public String getSalt() {
        return this.Salt;
    }
    public void setSalt(String Salt) {
        this.Salt = Salt;
    }
    public String getEndTime() {
        return this.EndTime;
    }
    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }
    public String getDepartmentName() {
        return this.DepartmentName;
    }
    public void setDepartmentName(String DepartmentName) {
        this.DepartmentName = DepartmentName;
    }
    public int getDeleteStatus() {
        return this.DeleteStatus;
    }
    public void setDeleteStatus(int DeleteStatus) {
        this.DeleteStatus = DeleteStatus;
    }
    public boolean getEnabled() {
        return this.Enabled;
    }
    public void setEnabled(boolean Enabled) {
        this.Enabled = Enabled;
    }
    public String getPhotoPath() {
        return this.PhotoPath;
    }
    public void setPhotoPath(String PhotoPath) {
        this.PhotoPath = PhotoPath;
    }
    public String getUserPinyin() {
        return this.UserPinyin;
    }
    public void setUserPinyin(String UserPinyin) {
        this.UserPinyin = UserPinyin;
    }
    public String getUserPinyinAbbr() {
        return this.UserPinyinAbbr;
    }
    public void setUserPinyinAbbr(String UserPinyinAbbr) {
        this.UserPinyinAbbr = UserPinyinAbbr;
    }
    public String getTitle() {
        return this.Title;
    }
    public void setTitle(String Title) {
        this.Title = Title;
    }
    public int getCurrencyAmount() {
        return this.CurrencyAmount;
    }
    public void setCurrencyAmount(int CurrencyAmount) {
        this.CurrencyAmount = CurrencyAmount;
    }
    public int getPrivateDealCount() {
        return this.PrivateDealCount;
    }
    public void setPrivateDealCount(int PrivateDealCount) {
        this.PrivateDealCount = PrivateDealCount;
    }
}
