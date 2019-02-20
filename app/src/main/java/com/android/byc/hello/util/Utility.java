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
    public static String ConvertUUIDToHexString(String str) {
        UUID uuid = ConvertStringToUUID(str);
        return ConvertUUIDToHexString(uuid);
    }

    public static String ConvertUUIDToHexString(UUID uuid) {
        if (uuid == null)
            return "null";
        String strLeast = String
                .format("%016x", uuid.getLeastSignificantBits());
        String strMost = String.format("%016x", uuid.getMostSignificantBits());

        char[] chMost = new char[16];
        chMost[0] = strMost.charAt(6);
        chMost[1] = strMost.charAt(7);
        chMost[2] = strMost.charAt(4);
        chMost[3] = strMost.charAt(5);
        chMost[4] = strMost.charAt(2);
        chMost[5] = strMost.charAt(3);
        chMost[6] = strMost.charAt(0);
        chMost[7] = strMost.charAt(1);
        chMost[8] = strMost.charAt(10);
        chMost[9] = strMost.charAt(11);
        chMost[10] = strMost.charAt(8);
        chMost[11] = strMost.charAt(9);
        chMost[12] = strMost.charAt(14);
        chMost[13] = strMost.charAt(15);
        chMost[14] = strMost.charAt(12);
        chMost[15] = strMost.charAt(13);

        return String.format("X'%s'", String.valueOf(chMost) + strLeast);
    }

    public static UUID ConvertBytesToUUID(byte[] bytes) {
        if (bytes == null)
            return null;
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return ConvertHexStringToUUID(sb.toString());
    }
    public static String ConvertBytesToHexString(byte[] bytes) {
        if (bytes == null)
            return "null";
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return String.format("X'%s'", sb.toString());
    }

    public static UUID ConvertHexStringToUUID(String str) {
        if (str == null)
            return null;
        str = str.toLowerCase().replace("x", "").replace("'", "");
        if (str.length() != 32)
            return null;
        String strMost = str.substring(0, 16);
        char[] chMost = new char[16];
        chMost[0] = strMost.charAt(6);
        chMost[1] = strMost.charAt(7);
        chMost[2] = strMost.charAt(4);
        chMost[3] = strMost.charAt(5);
        chMost[4] = strMost.charAt(2);
        chMost[5] = strMost.charAt(3);
        chMost[6] = strMost.charAt(0);
        chMost[7] = strMost.charAt(1);
        chMost[8] = strMost.charAt(10);
        chMost[9] = strMost.charAt(11);
        chMost[10] = strMost.charAt(8);
        chMost[11] = strMost.charAt(9);
        chMost[12] = strMost.charAt(14);
        chMost[13] = strMost.charAt(15);
        chMost[14] = strMost.charAt(12);
        chMost[15] = strMost.charAt(13);
        strMost = String.valueOf(chMost);
        String result = String.format("%s-%s-%s-%s-%s",
                strMost.substring(0, 8), strMost.substring(8, 12),
                strMost.substring(12, 16), str.substring(16, 20),
                str.substring(20));
        return UUID.fromString(result);
    }
}
