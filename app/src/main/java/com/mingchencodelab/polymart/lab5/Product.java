package com.mingchencodelab.polymart.lab5;

import androidx.annotation.NonNull;

public class Product {
    private String pid;
    private String name;
    private String price;
    private String description;

    public Product() {
    }

    public Product(String product_id, String name, String price, String description) {
        this.pid = product_id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return "Product{" +
                "id='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
