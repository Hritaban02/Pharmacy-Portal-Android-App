package com.example.pharmacyportal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartMedicinesAdapter extends RecyclerView.Adapter<CartMedicinesAdapter.CartMedicinesViewHolder> {

    private final Context mContext;
    private final ArrayList<OrderMedicine> mCartMedicinesArray;

    public CartMedicinesAdapter(Context context, ArrayList<OrderMedicine> arrayCartMedicines) {
        mContext = context;
        mCartMedicinesArray = arrayCartMedicines;
    }

    @NonNull
    @Override
    public CartMedicinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_medicine_cart, parent, false);
        return new CartMedicinesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartMedicinesViewHolder holder, int position) {
        OrderMedicine medicineInCart = mCartMedicinesArray.get(position);

        String priceTXT = "\u20B9 " + medicineInCart.sellingPrice;

        holder.id.setText(String.valueOf(medicineInCart.medicineId));
        holder.title.setText(medicineInCart.tradeName);
        holder.quantity.setText(String.valueOf(medicineInCart.quantity));
        holder.price.setText(priceTXT);
    }

    @Override
    public int getItemCount() {
        return mCartMedicinesArray.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class CartMedicinesViewHolder extends RecyclerView.ViewHolder {

        CardView card;
        TextView id, title, quantity, price;

        CartMedicinesViewHolder(View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.id);
            card = (CardView) itemView.findViewById(R.id.card);
            title = (TextView) itemView.findViewById(R.id.title);
            quantity = (TextView) itemView.findViewById(R.id.quantity);
            price = (TextView) itemView.findViewById((R.id.price));
        }

    }
}
