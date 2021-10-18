package com.seongbinsoft.pickupasap;

import java.util.ArrayList;

public class Receipt_Item {
    public String date;
    public String name;
    public String msg;
    public String status;
    public String img;
    public String latitude;
    public String longitude;
    public ArrayList<MenuList_Item> menu;

    public Receipt_Item() {
    }

    public Receipt_Item(String date, String name, String msg, String status, String img, String latitude, String longitude, ArrayList<MenuList_Item> menu) {
        this.date = date;
        this.name = name;
        this.msg = msg;
        this.status = status;
        this.img = img;
        this.latitude = latitude;
        this.longitude = longitude;
        this.menu = menu;
    }
}
