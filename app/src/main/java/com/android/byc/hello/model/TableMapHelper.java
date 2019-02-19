package com.android.byc.hello.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/19 14:23
 * @description
 */
public final class TableMapHelper {
    static {
        getInstance().put("OutDoorAttendanceRecords", "com.fooww.soft.android.DataModel.Entity.OutDoorAttendanceRecordsEntity");
        getInstance().put("AttendanceRules", "com.fooww.soft.android.DataModel.Entity.AttendanceRulesEntity");
        getInstance().put("FavoriteHouses", "com.fooww.soft.android.DataModel.Entity.FavoriteHousesEntity");
        getInstance().put("HouseCompanyCommunities", "com.fooww.soft.android.DataModel.Entity.HouseCompanyCommunitiesEntity");
        getInstance().put("Users", "com.fooww.soft.android.DataModel.Entity.UsersEntity");
        getInstance().put("EstateCustomersListModel", "com.fooww.soft.android.DataModel.Entity.EstateCustomersListModelEntity");
        getInstance().put("EstateKeywordHistory", "com.fooww.soft.android.DataModel.Entity.EstateKeywordHistoryEntity");
        getInstance().put("ChatArticles", "com.fooww.soft.android.DataModel.Entity.ChatMessageArticle");
        getInstance().put("PersonalShareEmoji", "com.fooww.soft.android.Presentation.MultipleShare.entities.PersonalShareEmojiEntity");
        getInstance().put("AttendanceLocations", "com.fooww.soft.android.DataModel.Entity.AttendanceLocationsEntity");
        getInstance().put("ContactCellphones", "com.fooww.soft.android.DataModel.Entity.ContactCellphonesEntity");
        getInstance().put("ChatCurrencies", "com.fooww.soft.android.DataModel.Entity.ChatMessageIntegral");
        getInstance().put("UserAlias", "com.fooww.soft.android.DataModel.Entity.UserAliasEntity");
        getInstance().put("CompanyDepartments", "com.fooww.soft.android.DataModel.Entity.CompanyDepartmentsEntity");
        getInstance().put("Regions", "com.fooww.soft.android.DataModel.Entity.RegionsEntity");
        getInstance().put("Roles", "com.fooww.soft.android.DataModel.Entity.RolesEntity");
        getInstance().put("LevelApplications", "com.fooww.soft.android.DataModel.Entity.LevelApplicationsEntity");
        getInstance().put("EstateCustomerTrackings", "com.fooww.soft.android.DataModel.Entity.EstateCustomerTrackingsEntity");
        getInstance().put("HouseVisitRecordsModel", "com.fooww.soft.android.DataModel.Entity.HouseVisitRecordsModel");
        getInstance().put("ServiceTaskBundle", "com.fooww.soft.android.DataModel.Entity.ServiceTaskBundleEntity");
        getInstance().put("BaseBuildingTypes", "com.fooww.soft.android.DataModel.Entity.BaseBuildingTypesEntity");
        getInstance().put("FoowwUserFriends", "com.fooww.soft.android.DataModel.Entity.FoowwUserFriendsEntity");
        getInstance().put("HouseTags", "com.fooww.soft.android.DataModel.Entity.HouseTagsEntity");
        getInstance().put("Cities", "com.fooww.soft.android.DataModel.Entity.CitiesEntity");
        getInstance().put("HouseDealNew", "com.fooww.soft.android.DataModel.Entity.HouseDealNewEntity");
        getInstance().put("SNSUsers", "com.fooww.soft.android.DataModel.Entity.SNSUsers");
        getInstance().put("HouseDealSecond", "com.fooww.soft.android.DataModel.Entity.HouseDealSecondEntity");
        getInstance().put("HouseDemandListModel", "com.fooww.soft.android.DataModel.Entity.HouseDemandListModelEntity");
        getInstance().put("ContactPhones", "com.fooww.soft.android.DataModel.Entity.ContactPhonesEntity");
        getInstance().put("HouseDealRent", "com.fooww.soft.android.DataModel.Entity.HouseDealRentEntity");
        getInstance().put("Houses", "com.fooww.soft.android.DataModel.Entity.HousesEntity");
        getInstance().put("CourseAlbums", "com.fooww.soft.android.FoowwCollege.entity.CourseAlbumsEntity");
        getInstance().put("TableLastSyncTime", "com.fooww.soft.android.DataModel.Entity.TableLastSyncTimeEntity");
        getInstance().put("FoowwReminderGroups", "com.fooww.soft.android.DataModel.Entity.FoowwReminderGroupsEntity");
        getInstance().put("UserModel", "com.fooww.soft.android.DataModel.Entity.UserModelEntity");
        getInstance().put("CityDomain", "com.fooww.soft.android.DataModel.Entity.CityDomainEntity");
        getInstance().put("CompanyDemandNew", "com.fooww.soft.android.DataModel.Entity.CompanyDemandNewEntity");
        getInstance().put("CompanyDemandRent", "com.fooww.soft.android.DataModel.Entity.CompanyDemandRentEntity");
        getInstance().put("HouseImagesVR", "com.fooww.soft.android.DataModel.Entity.HouseImagesVREntity");
        getInstance().put("CompanyBlocks", "com.fooww.soft.android.DataModel.Entity.CompanyBlocksEntity");
        getInstance().put("ObjectImages", "com.fooww.soft.android.DataModel.Entity.ObjectImagesEntity");
        getInstance().put("HouseVideos", "com.fooww.soft.android.video.entity.HouseVideos");
        getInstance().put("FoowwReminderUsers", "com.fooww.soft.android.DataModel.Entity.FoowwReminderUsersEntity");
        getInstance().put("VipHouseKeywordHistory", "com.fooww.soft.android.DataModel.Entity.VipHouseKeywordHistoryEntity");
        getInstance().put("Blocks", "com.fooww.soft.android.DataModel.Entity.BlocksEntity");
        getInstance().put("BuildingTypes", "com.fooww.soft.android.DataModel.Entity.BuildingTypesEntity");
        getInstance().put("HouseVisitRecords", "com.fooww.soft.android.DataModel.Entity.HouseVisitRecordsEntity");
        getInstance().put("CompanyGroups", "com.fooww.soft.android.DataModel.Entity.CompanyGroupsEntity");
        getInstance().put("ChatMessages", "com.fooww.soft.android.DataModel.Entity.ChatMessages");
        getInstance().put("PublicHolidays", "com.fooww.soft.android.DataModel.Entity.PublicHolidaysEntity");
        getInstance().put("HouseDealTags", "com.fooww.soft.android.DataModel.Entity.HouseDealTagsEntity");
        getInstance().put("Facilities", "com.fooww.soft.android.DataModel.Entity.FacilitiesEntity");
        getInstance().put("ChatOwnershipNotifies", "com.fooww.soft.android.DataModel.Entity.ChatOwnershipNotifyEntity");
        getInstance().put("CompanyDistricts", "com.fooww.soft.android.DataModel.Entity.CompanyDistrictsEntity");
        getInstance().put("CompanyCommunities", "com.fooww.soft.android.DataModel.Entity.CompanyCommunitiesEntity");
        getInstance().put("MobileAttendanceUsers", "com.fooww.soft.android.DataModel.Entity.MobileAttendanceUsersEntity");
        getInstance().put("ChatImages", "com.fooww.soft.android.DataModel.Entity.ChatMessageImage");
        getInstance().put("ReportEvents", "com.fooww.soft.android.DataModel.Entity.ReportEventsEntity");
        getInstance().put("CurrencyRecords", "com.fooww.soft.android.DataModel.Entity.CurrencyRecordsEntity");
        getInstance().put("DemandCustomers", "com.fooww.soft.android.DataModel.Entity.DemandCustomersEntity");
        getInstance().put("HouseDemandRent", "com.fooww.soft.android.DataModel.Entity.HouseDemandRentEntity");
        getInstance().put("CellphoneViewedRecords", "com.fooww.soft.android.DataModel.Entity.CellphoneViewedRecordsEntity");
        getInstance().put("Communities", "com.fooww.soft.android.DataModel.Entity.CommunitiesEntity");
        getInstance().put("Companies", "com.fooww.soft.android.DataModel.Entity.CompaniesEntity");
        getInstance().put("Prices", "com.fooww.soft.android.DataModel.Entity.PricesEntity");
        getInstance().put("CompanyDemandSecond", "com.fooww.soft.android.DataModel.Entity.CompanyDemandSecondEntity");
        getInstance().put("BusinessTracking", "com.fooww.soft.android.DataModel.Entity.BusinessTrackingEntity");
        getInstance().put("HouseListModel", "com.fooww.soft.android.DataModel.Entity.HouseListModelEntity");
        getInstance().put("ChatMultiArticles", "com.fooww.soft.android.DataModel.Entity.MultiArticleEntity");
        getInstance().put("CourseComments", "com.fooww.soft.android.FoowwCollege.entity.CourseCommentsEntity");
        getInstance().put("HouseSurveyRecords", "com.fooww.soft.android.DataModel.Entity.HouseSurveyRecordsEntity");
        getInstance().put("AttendanceSpecialDays", "com.fooww.soft.android.DataModel.Entity.AttendanceSpecialDaysEntity");
        getInstance().put("BuildingTypeFacility", "com.fooww.soft.android.DataModel.Entity.BuildingTypeFacilityEntity");
        getInstance().put("ChatVoices", "com.fooww.soft.android.DataModel.Entity.ChatMessageAudio");
        getInstance().put("SoftCrashLogs", "com.fooww.soft.android.DataModel.Entity.SoftCrashLogsEntity");
        getInstance().put("FoowwMobilePermissions", "com.fooww.soft.android.DataModel.Entity.FoowwMobilePermissionsEntity");
        getInstance().put("CourseAds", "com.fooww.soft.android.FoowwCollege.entity.CourseAdsEntity");
        getInstance().put("WeizhanStatistics", "com.fooww.soft.android.DataModel.Entity.ChatMessageStatistic");
        getInstance().put("HouseDemandIntentionHouses", "com.fooww.soft.android.DataModel.Entity.HouseDemandIntentionHousesEntity");
        getInstance().put("CompanySettingOverdue", "com.fooww.soft.android.DataModel.Entity.CompanySettingOverdueEntity");
        getInstance().put("Customers", "com.fooww.soft.android.DataModel.Entity.CustomersEntity");
        getInstance().put("HouseImages", "com.fooww.soft.android.DataModel.Entity.HouseImagesEntity");
        getInstance().put("FeatureEventTrackCustom", "com.fooww.soft.android.event_track.models.FeatureEventTrackCustomEntity");
        getInstance().put("AbsenceApplications", "com.fooww.soft.android.DataModel.Entity.AbsenceApplicationsEntity");
        getInstance().put("CompanyCommunityBuildings", "com.fooww.soft.android.DataModel.Entity.CompanyCommunityBuildingsEntity");
        getInstance().put("SNSUserFriends", "com.fooww.soft.android.DataModel.Entity.SNSUserFriends");
        getInstance().put("FavoriteVipHouseKeywordHistory", "com.fooww.soft.android.DataModel.Entity.FavoriteVipHouseKeywordHistoryEntity");
        getInstance().put("CurrencyTasks", "com.fooww.soft.android.DataModel.Entity.CurrencyTasksEntity");
        getInstance().put("HouseImageTags", "com.fooww.soft.android.house.HouseImageTags");
        getInstance().put("ApplicationLogs", "com.fooww.soft.android.DataModel.Entity.ApplicationLogsEntity");
        getInstance().put("Courses", "com.fooww.soft.android.FoowwCollege.entity.CoursesEntity");
        getInstance().put("BlackList", "com.fooww.soft.android.DataModel.Entity.BlackListEntity");
        getInstance().put("CompanyRegions", "com.fooww.soft.android.DataModel.Entity.CompanyRegionsEntity");
        getInstance().put("CompanyRegionDepartments", "com.fooww.soft.android.DataModel.Entity.CompanyRegionDepartmentsEntity");
        getInstance().put("FeatureEventTracks", "com.fooww.soft.android.DataModel.Entity.FeatureEventTracksEntity");
        getInstance().put("Decorations", "com.fooww.soft.android.DataModel.Entity.DecorationsEntity");
        getInstance().put("HouseDemandNew", "com.fooww.soft.android.DataModel.Entity.HouseDemandNewEntity");
        getInstance().put("CompanyUsers", "com.fooww.soft.android.DataModel.Entity.CompanyUsersEntity");
        getInstance().put("ChatLinks", "com.fooww.soft.android.DataModel.Entity.ChatMessageShareUrl");
        getInstance().put("RoleCompanyUsers", "com.fooww.soft.android.DataModel.Entity.RoleCompanyUsersEntity");
        getInstance().put("Districts", "com.fooww.soft.android.DataModel.Entity.DistrictsEntity");
        getInstance().put("ContactInfos", "com.fooww.soft.android.DataModel.Entity.ContactEntity");
        getInstance().put("CurrencyTaskRecords", "com.fooww.soft.android.DataModel.Entity.CurrencyTaskRecordsEntity");
        getInstance().put("Changes", "com.fooww.soft.android.DataModel.Entity.ChangesEntity");
        getInstance().put("RelatedInfo", "com.fooww.soft.android.DataModel.Entity.RelatedInfoEntity");
        getInstance().put("HouseImageTasks", "com.fooww.soft.android.DataModel.Entity.HouseImageTasks");
        getInstance().put("SignRecords", "com.fooww.soft.android.DataModel.Entity.SignRecordsEntity");
        getInstance().put("CourseWatchedRecords", "com.fooww.soft.android.FoowwCollege.entity.CourseWatchedRecordsEntity");
        getInstance().put("Provinces", "com.fooww.soft.android.DataModel.Entity.ProvincesEntity");
        getInstance().put("HouseDemandSecond", "com.fooww.soft.android.DataModel.Entity.HouseDemandSecondEntity");
        getInstance().put("CompanyGroupUsers", "com.fooww.soft.android.DataModel.Entity.CompanyGroupUsersEntity");
        getInstance().put("FoowwReminders", "com.fooww.soft.android.DataModel.Entity.FoowwRemindersEntity");
        getInstance().put("QueryHouseKeywordHistory", "com.fooww.soft.android.DataModel.Entity.QueryHouseKeywordHistoryEntity");
        getInstance().put("RoleUsers", "com.fooww.soft.android.DataModel.Entity.RoleUsersEntity");
        getInstance().put("FoowwUsers", "com.fooww.soft.android.DataModel.Entity.FoowwUsersEntity");
    }

    private final Map tableEntity = new HashMap<String, String>();

    private TableMapHelper() {
    }

    public static TableMapHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private void put(String table, String entity) {
        tableEntity.put(table, entity);
    }

    public String getEntityByTable(String table) {
        return (String)tableEntity.get(table);
    }

    public <T> String getTableByEntity(T entity) {
        return getTableByEntity(entity.getClass());
    }

    public String getTableByEntity(Class cls) {
        return getTableByEntity(cls.getCanonicalName());
    }

    public String getTableByEntity(String entity) {
        for (HashMap.Entry<String, String> entry : (Set<HashMap.Entry<String, String>>)tableEntity.entrySet()) {
            if (entity.equals(entry.getValue()))
                return (String)entry.getKey();
        }
        return null;
    }

    private static final class SingletonHolder {
        static TableMapHelper INSTANCE = new TableMapHelper();
    }
}
