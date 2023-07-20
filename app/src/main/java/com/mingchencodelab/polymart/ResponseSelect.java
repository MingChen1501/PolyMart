package com.mingchencodelab.polymart;

public class ResponseSelect {
    private Product[] products;
    private String message;

    public ResponseSelect() {
    }

    public ResponseSelect(Product[] products, String message) {
        this.products = products;
        this.message = message;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
