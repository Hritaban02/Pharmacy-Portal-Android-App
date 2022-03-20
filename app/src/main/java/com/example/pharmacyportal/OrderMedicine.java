package com.example.pharmacyportal;

public class OrderMedicine {
    int orderId;
    int medicineId;
    String tradeName;
    int quantity;
    double sellingPrice;

    public OrderMedicine(int orderId, int medicineId, String tradeName, int quantity, double sellingPrice) {
        this.orderId = orderId;
        this.medicineId = medicineId;
        this.tradeName = tradeName;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
    }
}
