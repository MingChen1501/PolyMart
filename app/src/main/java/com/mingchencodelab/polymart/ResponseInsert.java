package com.mingchencodelab.polymart;

public class ResponseInsert {
    private Product products;
    private String message;

    public ResponseInsert() {
    }

    public ResponseInsert(Product product, String message) {
        this.products = product;
        this.message = message;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
