package com.seongbinsoft.pickupasap;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("/PickUpAsap/shopArray.json")
    Call<RetrofitItem> getShopArray();
}
