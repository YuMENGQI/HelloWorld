package com.android.byc.hello.network;



import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.android.byc.hello.util.Utility;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/15 10:59
 * @description
 */
public class Data {
        public String TableName;
        public List<String> DataColumn = new ArrayList<>();
        @SerializedName("DataRowValue")
        public List<List<String>> DataRow = new LinkedList<>();

    public Data() {
    }

    public Data(JSONObject json) throws JSONException {
        TableName = json.getString("TableName");

        JSONArray jsonColumn = json.getJSONArray("DataColumn");
        for(int i = 0; i < jsonColumn.length(); i++){
            DataColumn.add(jsonColumn.getString(i));
        }

        JSONArray jsonRows = json.getJSONArray("DataRowValue");
        for(int i = 0; i < jsonRows.length(); i++){
            ArrayList<String> rowOne = new ArrayList<>();
            JSONArray jsonRow = jsonRows.getJSONArray(i);
            for(int j = 0; j < jsonRow.length(); j++){
                rowOne.add(jsonRow.getString(j));
            }
            this.DataRow.add(rowOne);
        }
    }

    public String GetCellValue(int row, int colIndex){
        String result = this.DataRow.get(row).get(colIndex);
        if (result.equals("{}")){
            result = null;
        }
        return result;
    }
    @Nullable
    public <T> T GetEntity(Class<T> c) throws IllegalArgumentException, IllegalAccessException, InstantiationException{
        return GetEntity(c, 0);
    }
    // 根据第rowIndex行数据，构造一个Entity对象
    public <T> T GetEntity(Class<T> c, int rowIndex) {
        if (this.DataRow.size() <= rowIndex)
            return null;

        try {
            T entity = c.newInstance();
            for (Field field: c.getFields()) {
                int columnIndex = findFieldIndex(field.getName());
                if (columnIndex < 0){
                    continue;
                }
                String cellValue = GetCellValue(rowIndex, columnIndex);
                String cellType = field.getType().getName();
                if (cellType.contentEquals("java.lang.String")){
                    field.set(entity, cellValue);
                }else if (cellType.contentEquals("java.util.UUID")){
                    field.set(entity, Utility.ConvertStringToUUID(cellValue));
                }else if (cellType.contentEquals("int") || cellType.contentEquals("byte") ||cellType.contentEquals("double") ||
                        cellType.contentEquals("long") || cellType.contentEquals("float")){
                    if (cellValue == null || cellValue.length() == 0){
                        cellValue = "0";
                    }
                    cellValue = cellValue.toLowerCase();
                    if (cellValue.contentEquals("true")){
                        cellValue = "1";
                    }else if (cellValue.contentEquals("false")){
                        cellValue = "0";
                    }
                    if (cellType.contentEquals("int")){
                        field.setInt(entity, Integer.valueOf(cellValue));
                    }else if (cellType.contentEquals("double")){
                        field.setDouble(entity, Double.valueOf(cellValue));
                    }else if (cellType.contentEquals("long")){
                        field.setLong(entity, Long.valueOf(cellValue));
                    }else if (cellType.contentEquals("float")){
                        field.setFloat(entity, Float.valueOf(cellValue));
                    }else if (cellType.contentEquals("byte")){
                        field.setByte(entity, Byte.valueOf(cellValue));
                    }
                }else if (cellType.contentEquals("boolean")){
                    if (cellValue == null || cellValue.length() == 0){
                        cellValue = "false";
                    }
                    if (cellValue.contentEquals("1")) {
                        cellValue = "true";
                    }else if (cellValue.contentEquals("0")){
                        cellValue = "false";
                    }
                    field.setBoolean(entity, Boolean.valueOf(cellValue));
                }
            }

            return entity;
        } catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
    @NonNull
    public <T> ArrayList<T> GetEntities(Class<T> c) throws IllegalArgumentException, IllegalAccessException, InstantiationException{
        ArrayList<T> entities = new ArrayList<>();
        Field[] fields = c.getFields();
        HashMap<Field, FieldModel> mapField = new HashMap<>();
        for(Field field : fields){
            int index = findFieldIndex(field.getName());
            String type = field.getType().getName();
            if (index >= 0){
                mapField.put(field, new FieldModel(index, type));
            }
        }
        String cellType, cellValue;
        for(int rowId = 0; rowId < DataRow.size(); rowId++){
            T entity = c.newInstance();
            Iterator<Field> itor = mapField.keySet().iterator();
            boolean needIgnore = false;
            while(itor.hasNext()){
                Field field = itor.next();
                cellValue = GetCellValue(rowId, mapField.get(field).index);
                cellType = mapField.get(field).typeName;
                if (cellType.equals("java.lang.String")){
                    field.set(entity, cellValue);
                } else if (cellType.equals("java.util.UUID")) {
                    field.set(entity, Utility.ConvertStringToUUID(cellValue));
                } else if (cellType.equals("java.lang.Integer") && !TextUtils.isEmpty(cellValue)) {
                    field.set(entity, Integer.valueOf(cellValue));
                } else if (cellType.equals("java.lang.Long") && !TextUtils.isEmpty(cellValue)) {
                    field.set(entity, Long.valueOf(cellValue));
                } else if (cellType.equals("java.lang.Double") && !TextUtils.isEmpty(cellValue)) {
                    field.set(entity, Double.valueOf(cellValue));
                } else if (cellType.equals("java.lang.Float") && !TextUtils.isEmpty(cellValue)) {
                    field.set(entity, Float.valueOf(cellValue));
                } else if (cellType.contentEquals("boolean")) {
                    if ("true".equals(cellValue.toLowerCase())){
                        field.set(entity, true);
                    }else {
                        field.set(entity, false);
                    }
                }else if (cellType.equals("int") || cellType.equals("byte") || cellType.equals("double") || cellType.equals("long") || cellType.equals("float")) {
                    if (cellValue == null || cellValue.length() == 0){
                        cellValue = "0";
                    }
                    cellValue = cellValue.toLowerCase();
                    if (cellValue.equals("true")){
                        cellValue = "1";
                    }else if (cellValue.equals("false")){
                        cellValue = "0";
                    }
                    if (cellType.equals("int")) {
                        field.setInt(entity, Integer.valueOf(cellValue));
                    }else if (cellType.equals("double")) {
                        field.setDouble(entity, Double.valueOf(cellValue));
                    }else if (cellType.equals("long")) {
                        field.setLong(entity, Long.valueOf(cellValue));
                    }else if (cellType.equals("float")) {
                        field.setFloat(entity, Float.valueOf(cellValue));
                    }else if (cellType.equals("byte")) {
                        field.setByte(entity, Byte.valueOf(cellValue));
                    }
                }else {
                    try {
                        Class fieldClazz = Class.forName(field.getType().getCanonicalName());

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
            if(!needIgnore){
                entities.add(entity);
            }

        }
        return entities;
    }

    class FieldModel{
        public int index;
        public String typeName;
        public FieldModel(int i, String type){
            index = i;
            typeName = type;
        }
    }

    /**
     * 将属性变成小写后作比较
     * @param fieldName fieldName
     * @return 属性的索引
     */
    private int findFieldIndex(String fieldName) {
        for (int i = 0; i < DataColumn.size(); i++) {
            if (DataColumn.get(i).toLowerCase().equals(fieldName.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public List<String> getDataColumn() {
        return DataColumn;
    }

    public void setDataColumn(List<String> dataColumn) {
        DataColumn = dataColumn;
    }

    public List<List<String>> getDataRow() {
        return DataRow;
    }

    public void setDataRow(List<List<String>> dataRow) {
        DataRow = dataRow;
    }
}
