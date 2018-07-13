package com.example.rakshit.bechde;

public class PostAd {
    String userID,title,desc;
    public PostAd(){

    }
    public PostAd(String id,String title,String desc){
        this.userID = id;
        this.title = title;
        this.desc = desc;
    }

    public String getUserID() {
        return userID;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
