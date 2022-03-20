package com.example.pharmacyportal;

public class Order {
    int id;
    String date;
    String paymentMethod;

    public Order(int id, String date, String paymentMethod) {
        this.id = id;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }
}
