package com.motivation.sendquiet.model;

import android.util.Log;

import java.io.Serializable;

public class User implements Serializable {

    String id;
    String name;
    int from;
    int to;
    String group;
    boolean isTeacher = false;

    public User() {
        // default constructor
    }

    public User(String id, String name, int from, int to, String group, boolean isTeacher) {
        this.id = id;
        this.name = name;
        this.from = from;
        this.to = to;
        this.group = group;
        this.isTeacher = isTeacher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getfrom() {
        return from;
    }

    public void setfrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    public void getUserInfo(String tag){
        Log.d(tag,
                    "ID : " + this.id
                        + "\nName :" + this.name
                        + "\nGroup : " + this.group
                        + "\nTo : " + this.to
                        + "\nfrom : " + this.from
                        + "\nisTeacher : " + this.isTeacher);
    }
}
