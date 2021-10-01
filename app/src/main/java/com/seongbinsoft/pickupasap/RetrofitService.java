package com.seongbinsoft.pickupasap;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("/PickUpAsap/shopArray.json")
    Call<ArrayList<ShopList_Item>> getShopArray();

    @GET("/PickUpAsap/shophori.json")
    Call<ArrayList<HomeFragHorizon_Item>> gethoriArray();

    @GET("/PickUpAsap/menuArray.json")
    Call<ArrayList<ShopInfo_Item>> getMenuArray();

    @GET("/PickUpAsap/shopReceipt.json")
    Call<ArrayList<Receipt_Item>> getReceiptArray();
}
