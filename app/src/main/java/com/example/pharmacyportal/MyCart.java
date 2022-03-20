package com.example.pharmacyportal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacyportal.databinding.ActivityMyCartBinding;

import java.util.ArrayList;

public class MyCart extends DrawerBaseActivity {

    ActivityMyCartBinding activityMyCartBinding;

    DBHelper DB;
    RecyclerView recyclerView;

    Button placeOrder, clearCart;

    TextView totalPrice;

    RadioGroup paymentMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMyCartBinding = ActivityMyCartBinding.inflate(getLayoutInflater());
        setContentView(activityMyCartBinding.getRoot());
        allocateActivityTitle("My Cart");

        DB = new DBHelper(this);

        placeOrder = (Button) findViewById(R.id.placeOrder);
        paymentMethod = (RadioGroup) findViewById(R.id.radioGroup);
        clearCart = (Button) findViewById(R.id.clearCart);
        totalPrice = (TextView) findViewById(R.id.totalPrice);

        clearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.clearCart();
                finish();
                startActivity(getIntent());
            }
        });

        paymentMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedRadioButton = (RadioButton) radioGroup.findViewById(i);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) {
                    DB.setPaymentMethod(checkedRadioButton.getText().toString());
                }
            }
        });

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.placeOrder();
                Intent orderIntent = new Intent(getApplicationContext(), Orders.class);
                startActivity(orderIntent);
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.orderMedicineList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<OrderMedicine> medicinesInCart = DB.getCartArray();

        if (medicinesInCart == null) {
            Toast.makeText(this, "No items in Cart", Toast.LENGTH_LONG).show();
        } else {
            double sum = 0;
            for (int i = 0; i < medicinesInCart.size(); i++) {
                sum += medicinesInCart.get(i).quantity * medicinesInCart.get(i).sellingPrice;
            }

            String priceTXT = "Total Price = \u20B9 " + sum;
            totalPrice.setText(priceTXT);

            CartMedicinesAdapter adapter = new CartMedicinesAdapter(this, medicinesInCart);
            recyclerView.setAdapter(adapter);
        }

    }
}