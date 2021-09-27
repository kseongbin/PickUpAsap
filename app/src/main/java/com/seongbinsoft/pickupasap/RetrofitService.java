package com.seongbinsoft.pickupasap;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("/PickUpAsap/shopArray.json")
    Call<ArrayList<ShopList_Item>> getShopArray();
}
