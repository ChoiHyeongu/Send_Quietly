package com.motivation.sendquiet.model;

public class User {

    String groupName;
    String usrename;
    int age;
    int gender;

    public User(String groupName, String usrename, int age, int gender) {
        this.groupName = groupName;
        this.usrename = usrename;
        this.age = age;
        this.gender = gender;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUsrename() {
        return usrename;
    }

    public void setUsrename(String usrename) {
        this.usrename = usrename;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
