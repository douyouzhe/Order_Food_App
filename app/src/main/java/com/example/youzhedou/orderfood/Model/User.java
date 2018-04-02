package com.example.youzhedou.orderfood.Model;

/**
 * Created by Youzhe Dou on 3/25/2018.
 */

public class User {
    private String name;
    private String  password;
    private String tel;
    private String isStaff;

    public String getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(String isStaff) {
        this.isStaff = isStaff;
    }


    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.isStaff = "false";

    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
