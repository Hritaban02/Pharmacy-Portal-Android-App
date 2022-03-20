package com.example.pharmacyportal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacyportal.databinding.ActivityOrdersBinding;

import java.util.ArrayList;

public class Orders extends DrawerBaseActivity {

    ActivityOrdersBinding activityOrdersBinding;

    DBHelper DB;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOrdersBinding = ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(activityOrdersBinding.getRoot());
        allocateActivityTitle("Order History");

        DB = new DBHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.orderList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<Order> orderArrayList = DB.getAllOrders();

        if (orderArrayList != null) {

            OrdersAdapter adapter = new OrdersAdapter(this, orderArrayList);
            adapter.setOnEntryClickListener(new OrdersAdapter.OnEntryClickListener() {
                @Override
                public void onEntryClick(View view, int position) {
                    TextView idView = (TextView) view.findViewById(R.id.id);
                    int id = Integer.parseInt(idView.getText().toString());

                    Intent detailIntent = new Intent(getApplicationContext(), OrderDetail.class);
                    detailIntent.putExtra("id", id);
                    startActivity(detailIntent);
                    finish();
                }
            });
            recyclerView.setAdapter(adapter);

        } else {
            Toast.makeText(this, "No orders placed yet", Toast.LENGTH_LONG).show();
        }
    }
}