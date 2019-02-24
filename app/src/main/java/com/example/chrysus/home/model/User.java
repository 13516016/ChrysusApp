package com.example.chrysus.home.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class User {
    private String name;
    private Integer balance;
    private String address;

    public User() {

    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }

    public String getStringBalance(){
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(this.balance);
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    private String phone;

    public User(String name, Integer balance, String address, String phone) {
        this.name = name;
        this.balance = balance;
        this.address = address;
        this.phone = phone;
    }

    public User(JSONObject json) throws JSONException {
        this.name = json.getString("name");
        this.balance = json.getInt("balance");
        this.phone = json.getString("phone");
        this.address = json.getString("address");
    }
}
