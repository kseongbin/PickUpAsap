package com.seongbinsoft.pickupasap;

import java.util.ArrayList;

public class ShopList_Item {

    public String name;
    public String msg;
    public String img;
    public String latitude;
    public String longitude;
    public String topic;
    public ArrayList<MenuList_Item> menu;

    public ShopList_Item() {
    }

    public ShopList_Item(String name, String msg, String img, String latitude, String longitude, String topic, ArrayList<MenuList_Item> menu) {
        this.name = name;
        this.msg = msg;
        this.img = img;
        this.latitude = latitude;
        this.longitude = longitude;
        this.topic = topic;
        this.menu = menu;
    }
}
