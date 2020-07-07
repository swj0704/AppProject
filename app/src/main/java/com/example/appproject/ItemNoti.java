package com.example.appproject;

public class ItemNoti {
    private String titleStr;
    private String contentStr;

    public ItemNoti(String titleStr, String contentStr){
        this.titleStr = titleStr;
        this.contentStr = contentStr;
    }

    public String getTitleStr() {
        return titleStr;
    }

    public String getContentStr() {
        return contentStr;
    }
}
