package com.seongbinsoft.pickupasap;

public class ShopInfo_Item {
    String name;
    String remain;
    String before;
    String dc;
    String after;
    int iv;

    public ShopInfo_Item() {
    }

    public ShopInfo_Item(String name, String remain, String before, String dc, String after, int iv) {
        this.name = name;
        this.remain = remain;
        this.before = before;
        this.dc = dc;
        this.after = after;
        this.iv = iv;
    }
}
