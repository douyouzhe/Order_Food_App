package com.example.youzhedou.orderfood.Model;

import java.util.List;

/**
 * Created by Youzhe Dou on 3/27/2018.
 */

public class Request {

    private String tel;
    private String name;
    private String address;
    private String total;

    private List<Order> foods;

    public Request() {
    }

    public Request(String tel, String name, String address, String total, List<Order> foods) {
        this.tel = tel;
        this.name = name;
        this.address = address;
        this.total = total;
        this.foods = foods;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}
