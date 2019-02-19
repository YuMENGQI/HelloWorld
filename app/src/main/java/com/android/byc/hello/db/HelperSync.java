package com.android.byc.hello.db;

import com.android.byc.hello.model.PermissionScope;
import com.android.byc.hello.model.PermissionType;
import com.android.byc.hello.util.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/19 14:24
 * @description
 */
public abstract class HelperSync {
    // 根据表名得到主键
    public static String GetPrimaryKey(String tableName) {
        if (tableName.equalsIgnoreCase("UserAlias")){
            return "CellPhone";
        }
        if (tableName.equalsIgnoreCase("SNSUsers")){
            return "PKUser";
        }
        if (tableName.equalsIgnoreCase("EstateCustomersListModel")){
            return "PKEstateUserCustomer";
        }
        if (tableName.equalsIgnoreCase("HouseVisitRecordsModel")) {
            return "PKHouseVisitRecord";
        }
        if(tableName.equalsIgnoreCase("HouseListModel")){
            return "PKHouseDeal";
        }
        if(tableName.equalsIgnoreCase("HouseDemandListModel")){
            return "PKCompanyDemand";
        }
        if(tableName.equalsIgnoreCase("Drafts")){
            return "PKObject";
        }
        if(tableName.equalsIgnoreCase("RelatedInfo")){
            return "PKObject";
        }
        if(tableName.equalsIgnoreCase("RoleUsers")){
            return "PKUser";
        }
        if(tableName.equalsIgnoreCase("UserModel")){
            return "PKUser";
        }
        if(tableName.equalsIgnoreCase("ContactPhones")){
            return "PKContactCellphone";
        }
        if(tableName.equalsIgnoreCase("CompanySettingOverdue")){
            return "PKCompany";
        }
        tableName = tableName.replace("Entity", "");
        String pKPropertyName;

        if (tableName.endsWith("ies")) {
            int len = tableName.length() - 3;
            tableName = tableName.substring(0, len) + "y";
        }else if (tableName.endsWith("s")) {
            int len = tableName.length() - 1;
            tableName = tableName.substring(0, len);
        }
        pKPropertyName = "PK" + tableName;
        return pKPropertyName;
    }

    public static String getDataValueInSQL(String columnValue, String columnType) {
        if (columnValue != null){
            if (columnValue.equalsIgnoreCase("{}")){
                columnValue = null;
            }else {
                columnValue = columnValue.trim();
                if (columnValue.indexOf('\'') >= 0)
                    columnValue = columnValue.replace("'", "''");
            }
        }
        if (columnType.equalsIgnoreCase("java.lang.String")){
            columnValue = columnValue == null ? "null" : String.format("'%s'", columnValue);
        }else if (columnType.equalsIgnoreCase("java.util.UUID")){
            columnValue = Utility.ConvertUUIDToHexString(columnValue);
        }else if (columnType.equalsIgnoreCase("int") || columnType.equalsIgnoreCase("byte") ||
                columnType.equalsIgnoreCase("long") || columnType.equalsIgnoreCase("double") ||
                columnType.equalsIgnoreCase("float")){
            if (columnValue == null || columnValue.length() == 0 || columnValue.equalsIgnoreCase("false")){
                columnValue="0";
            }if (columnValue.equalsIgnoreCase("true")){
                columnValue = "1";
            }
        }else if (columnType.equalsIgnoreCase("boolean")){
            if (columnValue == null || columnValue.length() == 0 || columnValue.equalsIgnoreCase("false")){
                columnValue = "0";
            }else if (columnValue.equalsIgnoreCase("true")){
                columnValue = "1";
            }else {
                columnValue = "0";
            }
        }else if (columnType.equalsIgnoreCase("java.util.Date")){
            columnValue = columnValue == null ? "null" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss hhh", Locale.CHINA).format(new Date(columnValue));
        }else if(columnValue == null){
            columnValue = "null";
        }else {
            try {
                Class fieldClazz = Class.forName(columnType);
                if(fieldClazz.isEnum()){
                    if("PermissionType".equals(fieldClazz.getSimpleName())){
                        PermissionType permissionType = PermissionType.valueOf(columnValue);
                        columnValue = String.valueOf(permissionType.getPermissionId());
                    }else if("PermissionScope".equals(fieldClazz.getSimpleName())){
                        PermissionScope permissionScope = PermissionScope.valueOf(columnValue);
                        columnValue = String.valueOf(permissionScope.getScopeValue());
                    }
                }else {
                    columnValue = String.format("'%s'", columnValue);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                columnValue = String.format("'%s'", columnValue);
            }

        }
        return columnValue;
    }
}
