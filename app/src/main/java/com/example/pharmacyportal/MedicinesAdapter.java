package com.example.pharmacyportal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MedicinesAdapter extends RecyclerView.Adapter<MedicinesAdapter.MedicinesViewHolder> {

    private final Context mContext;
    private final ArrayList<Medicine> mMedicinesArray;
    private OnEntryClickListener mOnEntryClickListener;

    public MedicinesAdapter(Context context, ArrayList<Medicine> arrayMedicines) {
        mContext = context;
        mMedicinesArray = arrayMedicines;
    }

    @NonNull
    @Override
    public MedicinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_medicine, parent, false);
        return new MedicinesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicinesViewHolder holder, int position) {
        Medicine medicine = mMedicinesArray.get(position);

        String priceTXT = "\u20B9 " + medicine.sellingPrice;

        holder.id.setText(String.valueOf(medicine.id));
        holder.title.setText(medicine.tradeName);
        holder.subtitle.setText(medicine.scientificName);
        holder.price.setText(priceTXT);
        holder.image.setImageBitmap(medicine.image);
    }

    @Override
    public int getItemCount() {
        return mMedicinesArray.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setOnEntryClickListener(OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public class MedicinesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView card;
        TextView id, title, subtitle, price;
        ImageView image;

        MedicinesViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            id = (TextView) itemView.findViewById(R.id.id);
            card = (CardView) itemView.findViewById(R.id.card);
            title = (TextView) itemView.findViewById(R.id.title);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
            price = (TextView) itemView.findViewById((R.id.price));
            image = (ImageView) itemView.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View view) {
            // The user may not set a click listener for list items, in which case our listener
            // will be null, so we need to check for this
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(view, getLayoutPosition());
            }
        }
    }
}
