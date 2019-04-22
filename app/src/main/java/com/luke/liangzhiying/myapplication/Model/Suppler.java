package com.luke.liangzhiying.myapplication.Model;

import java.sql.Time;

import javax.inject.Inject;

public class Suppler {
    String id;
    String name;     // string        `json:"name" bson:"name"`
    int type;      //int           `json:"type" bson:"type"`
    String phone;    // string        `json:"phone" bson:"phone"`
    String email;     //string        `json:"email" bson:"email"`
    String website;   //string        `json:"website" bson:"website"`
    String des;       //string        `json:"des" bson:"des"`
    int pstate;    //int           `json:"pstate" bson:"pstate"`
    int sstate;    //int           `json:"sstate" bson:"sstate"`
    int priority;  //int           `json:"priority" bson:"priority"`
    Time createdAt; //time.Time     `json:"created_at" bson:"created_at"`
    Time updatedAt;  //time.Time     `json:"updated_at" bson:"updated_at"`

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getDes() {
        return des;
    }

    public int getPstate() {
        return pstate;
    }

    public int getSstate() {
        return sstate;
    }

    public int getPriority() {
        return priority;
    }

    public Time getCreatedAt() {
        return createdAt;
    }

    public Time getUpdatedAt() {
        return updatedAt;
    }

    @Inject
    public Suppler() {
    }
}
