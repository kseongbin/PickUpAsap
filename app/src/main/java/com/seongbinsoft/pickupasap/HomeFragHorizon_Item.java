package com.seongbinsoft.pickupasap;

import java.util.ArrayList;

public class HomeFragHorizon_Item {
    public String horiimg;
    public String horiname;
    public String horimenu;
    public String one;
    public String horireal;
    public String horidc;
    public String latitude;
    public String longitude;
    public String topic;
    public ArrayList<MenuList_Item> menu;

    public HomeFragHorizon_Item() {
    }

    public HomeFragHorizon_Item(String horiimg, String horiname, String horimenu, String one, String horireal, String horidc, String latitude, String longitude, String topic, ArrayList<MenuList_Item> menu) {
        this.horiimg = horiimg;
        this.horiname = horiname;
        this.horimenu = horimenu;
        this.one = one;
        this.horireal = horireal;
        this.horidc = horidc;
        this.latitude = latitude;
        this.longitude = longitude;
        this.topic = topic;
        this.menu = menu;
    }
}
