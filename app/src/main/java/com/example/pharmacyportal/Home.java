package com.example.pharmacyportal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacyportal.databinding.ActivityHomeBinding;

import java.util.ArrayList;

public class Home extends DrawerBaseActivity {

    ActivityHomeBinding activityHomeBinding;

    DBHelper DB;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(activityHomeBinding.getRoot());
        allocateActivityTitle("Home");


        DB = new DBHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.medicineList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        Cursor all_medicines = DB.getAllMedicine();

        if (all_medicines.getCount() == 0) {
            Toast.makeText(Home.this, "No medicine in Inventory", Toast.LENGTH_LONG).show();
        } else {

            ArrayList<Medicine> arrayMedicines = new ArrayList<>();
            while (all_medicines.moveToNext()) {
                Medicine medicine_object = new Medicine(all_medicines.getInt(0), all_medicines.getString(1), all_medicines.getString(2), all_medicines.getDouble(3), all_medicines.getString(4), all_medicines.getString(5), all_medicines.getBlob(6));
                arrayMedicines.add(medicine_object);
            }
            MedicinesAdapter adapter = new MedicinesAdapter(this, arrayMedicines);
            adapter.setOnEntryClickListener(new MedicinesAdapter.OnEntryClickListener() {
                @Override
                public void onEntryClick(View view, int position) {
                    TextView idView = (TextView) view.findViewById(R.id.id);
                    int id = Integer.parseInt(idView.getText().toString());

                    Intent detailIntent = new Intent(getApplicationContext(), MedicineDetail.class);
                    detailIntent.putExtra("id", id);
                    startActivity(detailIntent);
                    finish();
                }
            });
            recyclerView.setAdapter(adapter);
        }


    }
}