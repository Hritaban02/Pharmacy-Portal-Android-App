package com.example.pharmacyportal;

import android.os.Bundle;

import com.example.pharmacyportal.databinding.ActivityBaseBinding;

public class Base extends DrawerBaseActivity {

    ActivityBaseBinding activityBaseBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBaseBinding = ActivityBaseBinding.inflate(getLayoutInflater());
        setContentView(activityBaseBinding.getRoot());
        allocateActivityTitle("Base");
    }
}