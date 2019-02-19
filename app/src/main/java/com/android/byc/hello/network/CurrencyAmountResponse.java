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

    public int getCurrencyAmount() {
        return currencyCoinsAmount;
    }

    public void setCurrencyAmount(int currencyCoinsAmount) {
        this.currencyCoinsAmount = currencyCoinsAmount;
    }
}
