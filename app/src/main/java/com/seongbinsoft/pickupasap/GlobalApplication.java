package com.seongbinsoft.pickupasap;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class GlobalApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this, "1b0ee869d103812d18383c935416527e");
    }
}
