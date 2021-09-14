package com.seongbinsoft.pickupasap;

public class Receipt_Item {
    public String date;
    public String name;
    public String msg;
    public String status;
    public int img;

    public Receipt_Item() {
    }

    public Receipt_Item(String date, String name, String msg, String status, int img) {
        this.date = date;
        this.name = name;
        this.msg = msg;
        this.status = status;
        this.img = img;
    }
}
