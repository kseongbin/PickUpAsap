package com.seongbinsoft.pickupasap;

import java.util.ArrayList;

public class ShopList_Item {

    public String name;
    public String msg;
    public String img;
    public ArrayList<MenuList_Item> menu;

    public ShopList_Item() {
    }

    public ShopList_Item(String name, String msg, String img, ArrayList<MenuList_Item> menu) {
        this.name = name;
        this.msg = msg;
        this.img = img;
        this.menu = menu;
    }
}
