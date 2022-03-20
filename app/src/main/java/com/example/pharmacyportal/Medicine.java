package com.example.pharmacyportal;

import android.graphics.Bitmap;

public class Medicine {
    int id;
    String tradeName;
    String scientificName;
    double sellingPrice;
    String manufacturerName;
    String description;
    Bitmap image;

    public Medicine(int id, String tradeName, String scientificName, double sellingPrice, String manufacturerName, String description, byte[] image_byte) {
        this.id = id;
        this.tradeName = tradeName;
        this.scientificName = scientificName;
        this.sellingPrice = sellingPrice;
        this.manufacturerName = manufacturerName;
        this.description = description;
        this.image = DbBitmapUtility.getImage(image_byte);
    }
}
