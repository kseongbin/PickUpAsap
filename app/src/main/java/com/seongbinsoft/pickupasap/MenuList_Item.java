package com.seongbinsoft.pickupasap;

public class MenuList_Item {
    public String menuName;
    public String remain;
    public String before;
    public String dc;
    public String after;
    public String iv;

    public MenuList_Item() {
    }

    public MenuList_Item(String menuName, String remain, String before, String dc, String after, String iv) {
        this.menuName = menuName;
        this.remain = remain;
        this.before = before;
        this.dc = dc;
        this.after = after;
        this.iv = iv;
    }
}
