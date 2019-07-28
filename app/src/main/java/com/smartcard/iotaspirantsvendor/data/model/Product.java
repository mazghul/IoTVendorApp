package com.smartcard.iotaspirantsvendor.data.model;

import android.view.View;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;

public class Product {

    public String Code;
    private String Color;
    public String Description;
    private String Location;
    public String Name;
    public String Offer;
    public String Price;

    @SerializedName("Qnty in stock")
    private String Qnty;
    private String Size;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOffer() {
        return Offer;
    }

    public void setOffer(String offer) {
        Offer = offer;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQnty() {
        return Qnty;
    }

    public void setQnty(String qnty) {
        Qnty = qnty;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public void onCategoryClick(View view)
    {
        int a =1 ;
       // Toast.makeText("MAZ", Toast.LENGTH_LONG)
    }
}
