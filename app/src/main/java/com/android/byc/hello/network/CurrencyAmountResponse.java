package com.android.byc.hello.network;

import com.google.gson.annotations.SerializedName;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/15 12:20
 * @description
 */
public class CurrencyAmountResponse {

    @SerializedName("CurrencyAmount")
    private int currencyCoinsAmount;

    public int getCurrencyCoinsAmount() {
        return currencyCoinsAmount;
    }

    public void setCurrencyCoinsAmount(int currencyCoinsAmount) {
        this.currencyCoinsAmount = currencyCoinsAmount;
    }
}
