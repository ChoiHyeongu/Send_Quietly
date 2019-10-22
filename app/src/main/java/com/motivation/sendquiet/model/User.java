package com.motivation.sendquiet.model;

public class User {

    String id;
    String name;
    int send;
    int to;
    String group;
    boolean isTeacher = false;

    public User() {
        // default constructor
    }

    public User(String id, String name, int send, int to, String group, boolean isTeacher) {
        this.id = id;
        this.name = name;
        this.send = send;
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

    public int getSend() {
        return send;
    }

    public void setSend(int send) {
        this.send = send;
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
}
