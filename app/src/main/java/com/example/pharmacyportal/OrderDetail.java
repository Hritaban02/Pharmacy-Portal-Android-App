package com.example.pharmacyportal;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacyportal.databinding.ActivityOrderDetailBinding;

import java.util.ArrayList;

public class OrderDetail extends DrawerBaseActivity {

    ActivityOrderDetailBinding activityOrderDetailBinding;

    DBHelper DB;
    RecyclerView recyclerView;

    TextView totalPrice, date, paymentMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOrderDetailBinding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(activityOrderDetailBinding.getRoot());

        date = (TextView) findViewById(R.id.date);
        totalPrice = (TextView) findViewById(R.id.totalPrice);
        paymentMethod = (TextView) findViewById(R.id.paymentMethod);

        Bundle extras = getIntent().getExtras();
        int id;
        if (extras != null) {
            id = extras.getInt("id");
            //The key argument here must match that used in the other activity

            DB = new DBHelper(this);

            Order order = DB.getOrder(id);
            System.out.println(order.paymentMethod);
            String paymentMethodTXT = "Payment Method = " + order.paymentMethod;


            date.setText(order.date);
            paymentMethod.setText(paymentMethodTXT);

            recyclerView = (RecyclerView) findViewById(R.id.orderMedicineList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            ArrayList<OrderMedicine> orderMedicineArrayList = DB.getOrderMedicine(id);

            if (orderMedicineArrayList == null) {
                Toast.makeText(this, "No items in Cart", Toast.LENGTH_LONG).show();
            } else {
                double sum = 0;
                for (int i = 0; i < orderMedicineArrayList.size(); i++) {
                    sum += orderMedicineArrayList.get(i).quantity * orderMedicineArrayList.get(i).sellingPrice;
                }

                String priceTXT = "Total Price = \u20B9 " + sum;
                totalPrice.setText(priceTXT);

                CartMedicinesAdapter adapter = new CartMedicinesAdapter(this, orderMedicineArrayList);
                recyclerView.setAdapter(adapter);
            }

        }
    }
}