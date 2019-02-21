package com.example.chrysus.history;

public class Payment {
    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price;}

    public String getDate() { return date;}

    public void setDate(String date) { this.date = date; }

    String store;
    String status;
    String price;
    String date;

    public Payment(String store, String status, String price, String date) {
        this.store = store;
        this.status = status;
        this.price = price;
        this.date = date;
    }
}
