package com.android.byc.hello.network;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/15 10:56
 * @description
 */
public interface CoinsTaskApi {
    @FormUrlEncoded
    @POST("/Mobile/GetFoowwCurrency")
    Observable<Response<CurrencyAmountResponse>> getFoowwCurrency(
            @Field("PKUser") String pKUser
    );

    @FormUrlEncoded
    @POST("/Mobile/GetCurrencyTaskInfo")
    Observable<Response<List<Data>>> getCurrencyTaskInfo(
            @Field("PKUser") String pKUser,
            @Field("UpdateTime") String updateTime
    );
    /**
     * 获取排行榜信息
     * @param pKCompany pKCompany
     * @return 获取排行榜信息
     */
    @FormUrlEncoded
    @POST("/Mobile/GetCurrencyList")
    Observable<Response<List<Data>>> getCurrencyList(
            @Field("PKCompany") String pKCompany
    );
    /**
     * 获取房屋币收入支出列表
     * @param pKUser pKUser
     * @param updateTime updateTime
     * @return 房屋币收入支出列表
     */
    @FormUrlEncoded
    @POST("/Mobile/getCurrencyRecords")
    Observable<Response<List<Data>>> getCurrencyRecords(
            @Field("PKUser") String pKUser,
            @Field("UpdateTime") String updateTime
    );
}
