package com.android.byc.hello.model;

import java.util.EnumSet;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/19 14:25
 * @description
 */
public enum  PermissionType {
    //--1001	录入房源
    AddHouseDeal("AddHouseDeal", 1001),
    //--1002	录入新的小区
    AddNewCommunity("AddNewCommunity", 1002),
    //--1003	删除选中房源
    DeleteHouseDeal("DeleteHouseDeal", 1003),
    //--1004	有分配权限
    CanDistribute("CanDistribute", 1004),
    //--1005	审核房源
    AuditHouseDeal("AuditHouseDeal", 1005),
    //--1006	激活房源
    ActivateHouseDeal("ActivateHouseDeal", 1006),
    //--1007	上下线房源
    OnlineHouseDeal("OnlineHouseDeal", 1007),
    //--1008	房源置顶
    TopHouseDeal("TopHouseDeal", 1008),
    //--1015	手机端允许查看电话条数
    LimitWatchingOtherPhoneCountInMobile("LimitWatchingOtherPhoneCountInMobile", 1014),
    //--1016	个人房源查看电话条数
    LimitWatchingVIPHouseCount("LimitWatchingVIPHouseCount", 1015),

    //--1101	查看个人房源采集
    ViewVIPHouse("ViewVIPHouse", 1101),
    //--1104	修改公盘基本信息
    EditPublicHouseDealBase("EditPublicHouseDealBase", 1104),
    //--1105	修改公盘小区信息
    EditPublicHouseDealCommunity("EditPublicHouseDealCommunity", 1105),
    //--1106	修改公盘门牌
    EditPublicHouseDealDoorNumber("EditPublicHouseDealDoorNumber", 1106),
    //--1107	修改公盘业主信息
    EditPublicHouseDealCustomer("EditPublicHouseDealCustomer", 1107),
    //--1108	修改房源性质
    EditHouseDealModifier("EditHouseDealModifier", 1108),
    //--1109	修改导入房源的来源
//	EditHouseDealImportSource("EditHouseDealImportSource", 1109),
    //--1109	修改私盘基本信息
    EditPrivateHouseDealBase("EditPrivateHouseDealBase", 1109),
    //--1110	修改私盘小区信息
    EditPrivateHouseDealCommunity("EditPrivateHouseDealCommunity", 1110),
    //--1111	修改私盘门牌
    EditPrivateHouseDealDoorNumber("EditPrivateHouseDealDoorNumber", 1111),
    //--1112	修改私盘业主信息
    EditPrivateHouseDealCustomer("EditPrivateHouseDealCustomer", 1112),
    //--1113	查看私盘门牌
    ViewPrivateHouseDealDoorNumber("ViewPrivateHouseDealDoorNumber", 1113),
    //--1114	查看私盘业主信息
    ViewPrivateHouseDealCustomer("ViewPrivateHouseDealCustomer", 1114),
    //--1115	管理锁盘
    ManageLockedHouseDeal("ManageLockedHouseDeal", 1115),
    //--1116	管理特盘
    ManageSpecialHouseDeal("ManageSpecialHouseDeal", 1116),

    //--1201	添加房源照片
    AddHouseImage("AddHouseImage", 1201),
    //--1202	管理房源照片
    ManageHouseImage("ManageHouseImage", 1202),
    //--1203	删除实勘照片
    DeleteHouseSurveyImage("DeleteHouseSurveyImage", 1203),
    //--1205	查看电话无须跟进
    MustTrackWhenWatchingHouseDealPhone("MustTrackWhenWatchingHouseDealPhone", 1205),
    //--1206	查看日志保密信息
    ViewHouseDealSecrecyApplicationLog("ViewHouseDealSecrecyApplicationLog", 1206),

    //--2001	录入客源
    AddHouseDemand("AddHouseDemand", 2001),
    //--2002	激活客源
    ActivateHouseDemand("ActivateHouseDemand", 2002),
    //--2003	修改客源性质
    EditHouseDemandModifier("EditHouseDemandModifier", 2003),
    //--2005	删除客源
    DeleteHouseDemand("DeleteHouseDemand", 2005),

    //--2105	修改公客基本信息
    EditPublicHouseDemandBase("EditPublicHouseDemandBase", 2105),
    //--2106	修改公客保密信息
    EditPublicHouseDemandCustomer("EditPublicHouseDemandCustomer", 2106),
    //--2107	修改私客基本信息
    EditPrivateHouseDemandBase("EditPrivateHouseDemandBase", 2107),
    //--2108	修改私客保密信息
    EditPrivateHouseDemandCustomer("EditPrivateHouseDemandCustomer", 2108),
    //--2201	查看电话无须跟进
    MustTrackWhenWatchingHouseDemandPhone("MustTrackWhenWatchingHouseDemandPhone", 2201),
    //--2202	查看日志保密信息
    ViewHouseDemandSecrecyApplicationLog("ViewHouseDemandSecrecyApplicationLog", 2202),


    //--3201	删除房源跟进
    DeleteHouseDealTracking("DeleteHouseDealTracking", 3201),
    //--3202	删除客源跟进
    DeleteHouseDemandTracking("DeleteHouseDemandTracking", 3202),
    //--3203	添加带看
    AddHouseVisit("AddHouseVisit", 3203),
    //--3205	删除带看记录
    DeleteHouseVisitRecord("DeleteHouseVisitRecord", 3205),
    //--3301    查看考勤记录
    ViewAttendanceRecord("ViewAttendanceRecord", 3301),

    //--4001	管理公司
    ManageCompanyArchitect("ManageCompanyArchitect", 4001),//改权限只影响本店 公司 区域
    //--4012	新楼盘业务
    ViewNewEstate("ViewNewEstate", 4012),
    //--4201	在列表中显示门牌
    ShowDoorNumberInMobileHouseDealList("ShowDoorNumberInMobileHouseDealList", 4201),
    //--4202	查看公盘门牌
    ViewPublicDoorNumberInMobile("ViewPublicDoorNumberInMobile", 4202),
    //--4203	查看公盘业主信息
    ViewHouseDealCustomerInfoOfPublicInMobile("ViewHouseDealCustomerInfoOfPublicInMobile", 4203),
    //--4204	查看公客基本信息
    ViewHouseDealBaseInfoOfPublicInMobile("ViewHouseDealBaseInfoOfPublicInMobile", 4204),
    //--4205	查看公客保密信息
    ViewHouseDemandCustomerInfoOfPublicInMobile("ViewHouseDemandCustomerInfoOfPublicInMobile", 4205),
    //--4206	查看私客基本信息
    ViewHouseDemandBaseInfoOfPublicInMobile("ViewHouseDemandBaseInfoOfPublicInMobile", 4206),
    //--4207	查看私客保密信息
    ViewHouseDemandCustomerInfoOfPrivateInMobile("ViewHouseDemandCustomerInfoOfPrivateInMobile", 4207),
    //--4208	公盘自动共享发财圈
    AutoSharePublicHouseDeal("AutoSharePublicHouseDeal", 4208),
    //--4209	启用社交功能
    EnableSNS("EnableSNS", 4209),
    //--4302    查看隐号通话记录
    ViewVirtualPhoneRecord("ViewVirtualPhoneRecord", 4302),
    //--4303    录音播放权限
    RecordingMediaControl("RecordingMediaControl", 4303),
    //--4304	不开启房源隐号通话
    DisableHouseVirtualPhone("DisableHouseVirtualPhone", 4304),
    //--4304	不开启客源隐号通话
    DisableDemandVirtualPhone("DisableDemandVirtualPhone", 4305),
    //--1207	允许拥有私盘数量
    AllowPrivateDealCount("AllowPrivateDealCount", 1207);

    private String permissionName;
    private int permissionId;

    PermissionType(String permissionName, int permissionId) {
        this.permissionName = permissionName;
        this.permissionId = permissionId;
    }


    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public static PermissionType valueOf(int permissionId) {
        EnumSet<PermissionType> set = EnumSet.allOf(PermissionType.class);
        PermissionType permissionType = null;
        for (PermissionType type : set) {
            if (type.getPermissionId() == permissionId) {
                permissionType = type;
                break;
            }
        }
        return permissionType;
    }
}
