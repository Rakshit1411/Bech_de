package com.example.rakshit.bechde;

import java.util.Date;

public class PostAd {
    String userID,title,desc,picturePath,name;
    String date;
    public PostAd(){

    }
    public PostAd(String id,String title,String desc, String picturePath, String name,String date){
        this.userID = id;
        this.title = title;
        this.desc = desc;
        this.picturePath = picturePath;
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
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
    public String getPicturePath(){ return picturePath;}
}
