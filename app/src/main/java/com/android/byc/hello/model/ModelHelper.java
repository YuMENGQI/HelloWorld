package com.android.byc.hello.model;

import com.android.byc.hello.db.HelperSync;
import com.android.byc.hello.network.EntityIgnore;
import com.android.byc.hello.util.Utility;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/19 14:37
 * @description
 */
public abstract class ModelHelper {
    public static boolean checkNeedIgnore(Field field) {
        return field.getName().equals("$change")
                // 针对Parcelable接口中的静态常量
                || field.getName().equals("CREATOR")
                || field.getName().equals("CONTENTS_FILE_DESCRIPTOR")
                || field.getName().equals("PARCELABLE_WRITE_RETURN_VALUE")
                || field.getName().equals("PARCELABLE_ELIDE_DUPLICATES")
                || field.getName().equals("PARCELABLE_WRITE_RETURN_VALUE")

                || field.getName().equals("serialVersionUID")
                //  忽略字段
                || field.getAnnotation(EntityIgnore.class) != null;
    }

    public static <T> String createSQLs(T entity) {
        String[] sqls = new String[3];
        Class curClass = entity.getClass();
        Field[] fields = curClass.getFields();
        String tableName = curClass.getSimpleName().replace("Entity", "");
        String strPrimaryKey = HelperSync.GetPrimaryKey(tableName);
        try {
            String whereClause = String.format(" WHERE %s=%s", strPrimaryKey, Utility.ConvertUUIDToHexString((UUID)curClass.getField(strPrimaryKey).get(entity)));
            sqls[0] = "SELECT Count(*) AS CT FROM " + tableName + whereClause;
            StringBuilder sqlInsertPart1 = new StringBuilder();
            StringBuilder sqlInsertPart2 = new StringBuilder();
            StringBuilder sqlUpdate = new StringBuilder();
            sqlInsertPart1.append(String.format("INSERT INTO %s (", tableName));
            sqlInsertPart2.append(") VALUES (");
            sqlUpdate.append(String.format("UPDATE %s SET ", tableName));
            for(Field field : fields){
                if (checkNeedIgnore(field)){
                    continue;
                }
                String columnName = field.getName();
                String columnType = field.getType().getName();
                Object o = field.get(entity);
                String columnValue = o == null ? null : String.valueOf(o);
                columnValue = HelperSync.getDataValueInSQL(columnValue, columnType);
                sqlInsertPart1.append(String.format("%s,", columnName));
                sqlInsertPart2.append(String.format("%s,", columnValue));
                sqlUpdate.append(String.format("%s=%s,", columnName, columnValue));
            }
            // 去掉最后一个多余的逗号字符
            sqlInsertPart1.deleteCharAt(sqlInsertPart1.length() - 1);
            sqlInsertPart2.deleteCharAt(sqlInsertPart2.length() - 1);
            sqlUpdate.deleteCharAt(sqlUpdate.length() - 1);
            // 补足sql语句
            sqlInsertPart2.append(')');
            sqlUpdate.append(whereClause);
            sqls[1] = sqlInsertPart1.toString() + sqlInsertPart2.toString();
            sqls[2] = sqlUpdate.toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return sqls;
    }

    public static <T> String createInsertSQL(T entity) {
        Class curClass = entity.getClass();
        Field[] fields = curClass.getFields();
        String tableName = curClass.getSimpleName().replace("Entity", "");
        try {
            StringBuilder sqlInsertPart1 = new StringBuilder();
            StringBuilder sqlInsertPart2 = new StringBuilder();
            sqlInsertPart1.append(String.format("INSERT INTO %s (", tableName));
            sqlInsertPart2.append(") VALUES (");
            for(Field field : fields){
                if (checkNeedIgnore(field)){
                    continue;
                }
                String columnName = field.getName();
                String columnType = field.getType().getName();
                // end
                Object o = field.get(entity);
                // cx add 2017-4-12  Entity未初始化的属性, 不参与生成 此Insert语句
//				String columnValue = o == null ? null : String.valueOf(o);
                if (o == null) {
                    continue;
                }
                String columnValue =String.valueOf(o);
                // end
                columnValue = HelperSync.getDataValueInSQL(columnValue, columnType);
                sqlInsertPart1.append(String.format("%s,", columnName));
                sqlInsertPart2.append(String.format("%s,", columnValue));
            }
            //  去掉最后一个多余的逗号字符
            sqlInsertPart1.deleteCharAt(sqlInsertPart1.length() - 1);
            sqlInsertPart2.deleteCharAt(sqlInsertPart2.length() - 1);
            //  补足sql语句
            sqlInsertPart2.append(')');

            return sqlInsertPart1.toString() + sqlInsertPart2.toString();

        } catch (Exception e){
            return null;
        }
    }

    public static <T> List<String[]> createSQLs(Collection<T> entities)
            throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException{
        ArrayList<String[]> lstSqls = new ArrayList<>();
        if (entities == null || entities.size() <= 0)
            return lstSqls;
        Class curClass = entities.toArray()[0].getClass();
        Field[] fields = curClass.getFields();
        String tableName = curClass.getSimpleName().replace("Entity", "");
        String strPrimaryKey = HelperSync.GetPrimaryKey(tableName);
        Field fieldPK = curClass.getField(strPrimaryKey);
        String sqlQueryFormat = String.format("SELECT Count(*) AS CT FROM %s WHERE %s=%%s",tableName, strPrimaryKey);
        StringBuilder sqlInsertPart1 = new StringBuilder();
        StringBuilder sqlInsertPart2 = new StringBuilder();
        StringBuilder sqlUpdate = new StringBuilder();
        String columnName, columnType, columnValue;
        for(T entity : entities){
            sqlInsertPart1.delete(0, sqlInsertPart1.length());
            sqlInsertPart2.delete(0, sqlInsertPart2.length());
            sqlUpdate.delete(0, sqlUpdate.length());
            String[] sqls = new String[3];
            String primaryKeyHex = Utility.ConvertUUIDToHexString((UUID)fieldPK.get(entity));
            sqls[0] = String.format(sqlQueryFormat, primaryKeyHex);
            sqlInsertPart1.append(String.format("INSERT INTO %s (", tableName));
            sqlInsertPart2.append(") VALUES (");
            sqlUpdate.append(String.format("UPDATE %s SET ", tableName));
            for(Field field : fields){
                if (checkNeedIgnore(field)){
                    continue;
                }

                columnName = field.getName();
                columnType = field.getType().getName();
                // cx added 2016-11-23
                Object o = field.get(entity);
                columnValue = o == null ? "" : String.valueOf(o);
                //end
                columnValue = HelperSync.getDataValueInSQL(columnValue, columnType);
                sqlInsertPart1.append(String.format("%s,", columnName));
                sqlInsertPart2.append(String.format("%s,", columnValue));
                sqlUpdate.append(String.format("%s=%s,", columnName, columnValue));
            }
            // 去掉最后一个多余的逗号字符
            sqlInsertPart1.deleteCharAt(sqlInsertPart1.length() - 1);
            sqlInsertPart2.deleteCharAt(sqlInsertPart2.length() - 1);
            sqlUpdate.deleteCharAt(sqlUpdate.length() - 1);
            // 补足sql语句
            sqlInsertPart2.append(')');
            sqlUpdate.append(String.format(" WHERE %s=%s", strPrimaryKey, primaryKeyHex));
            sqls[1] = sqlInsertPart1.toString() + sqlInsertPart2.toString();
            sqls[2] = sqlUpdate.toString();
            lstSqls.add(sqls);
        }
        return lstSqls;
    }


    public static <T> String[] createDeleteSqls(T entity) {
        String[] sqls = new String[2];
        Class curClass = entity.getClass();
        String tableName = curClass.getSimpleName().replace("Entity", "");
        String strPrimaryKey = HelperSync.GetPrimaryKey(tableName);
        try {
            String whereClause = String.format(" WHERE %s=%s", strPrimaryKey, Utility.ConvertUUIDToHexString((UUID)curClass.getField(strPrimaryKey).get(entity)));
            sqls[0] = "SELECT Count(*) AS CT FROM " + tableName + whereClause;
            sqls[1] = String.format("DELETE FROM %s %s", tableName, whereClause);
        } catch (Exception e){
            e.printStackTrace();
        }
        return sqls;
    }

    public static <T> String[] createUpdateSqls(T entity) {
        String[] sqls = new String[3];//此处sqls[1]为null，保持统一
        Class curClass = entity.getClass();
        Field[] fields = curClass.getFields();
        String tableName = curClass.getSimpleName().replace("Entity", "");
        String strPrimaryKey = HelperSync.GetPrimaryKey(tableName);
        try {
            String whereClause = String.format(" WHERE %s=%s", strPrimaryKey, Utility.ConvertUUIDToHexString((UUID)curClass.getField(strPrimaryKey).get(entity)));
            sqls[0] = "SELECT Count(*) AS CT FROM " + tableName + whereClause;
            StringBuilder sqlUpdate = new StringBuilder();
            sqlUpdate.append(String.format("UPDATE %s SET ", tableName));
            for(Field field : fields){
                if (checkNeedIgnore(field)){
                    continue;
                }
                String columnName = field.getName();
                String columnType = field.getType().getName();
                Object o = field.get(entity);
                String columnValue = o == null ? null : String.valueOf(o);
                columnValue = HelperSync.getDataValueInSQL(columnValue, columnType);
                sqlUpdate.append(String.format("%s=%s,", columnName, columnValue));
            }
            sqlUpdate.deleteCharAt(sqlUpdate.length() - 1);
            sqlUpdate.append(whereClause);
            sqls[2] = sqlUpdate.toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return sqls;
    }
}
