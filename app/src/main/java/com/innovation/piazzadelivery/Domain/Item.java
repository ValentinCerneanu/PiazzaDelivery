package com.innovation.piazzadelivery.Domain;

import android.graphics.Bitmap;

public class Item {
    private String key;
    private String name;
    private String description;
    private String categoryKey;
    private Double price;
    private Double gramaj;
    private String picture;
    private Bitmap bitmap;
    private int quantity;

    public Item(String key, String categoryKey, String name, String description, Double price, Double gramaj, String picture) {
        this.key = key;
        this.categoryKey = categoryKey;
        this.name = name;
        this.description = description;
        this.price = price;
        this.gramaj = gramaj;
        this.picture = picture;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getGramaj() {
        return gramaj;
    }

    public void setGramaj(Double gramaj) {
        this.gramaj = gramaj;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey;
    }
}
