package com.seongbinsoft.pickupasap;

public class ShopInfo_Item {
    String name;
    String remain;
    String before;
    String dc;
    String after;
    String iv;

    public ShopInfo_Item() {
    }

    public ShopInfo_Item(String name, String remain, String before, String dc, String after, String iv) {
        this.name = name;
        this.remain = remain;
        this.before = before;
        this.dc = dc;
        this.after = after;
        this.iv = iv;
    }
}
