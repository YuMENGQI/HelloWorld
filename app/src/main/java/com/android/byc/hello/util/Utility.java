package com.android.byc.hello.util;

import com.android.byc.hello.network.Data;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.UUID;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/15 11:17
 * @description
 */
public class Utility {
    public static UUID ConvertStringToUUID(String str) {
        if (str == null || str.trim().length() < 10) {
            return null;
        }
        try {
            return UUID.fromString(str);
        } catch (Exception e) {
            return null;
        }
    }
    public static Data handleCoinsResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("CurrencyAmount");
            String currencyAmount = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(currencyAmount, Data.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
