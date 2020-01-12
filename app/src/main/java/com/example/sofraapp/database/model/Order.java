package com.example.sofraapp.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Order {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo
    String photo;
    @ColumnInfo
    String name;
    @ColumnInfo
    String desc;
    @ColumnInfo
    String price;
    @ColumnInfo
    String offer_price;
    @ColumnInfo
    String request;
    @ColumnInfo
    String number;

    public Order(String photo, String name, String desc, String price, String offer_price, String request, String number) {
        this.photo = photo;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.offer_price = offer_price;
        this.request = request;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(String offer_price) {
        this.offer_price = offer_price;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
