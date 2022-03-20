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

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {

    private final Context mContext;
    private final ArrayList<Order> mOrdersArray;
    private OnEntryClickListener mOnEntryClickListener;

    public OrdersAdapter(Context context, ArrayList<Order> arrayOrders) {
        mContext = context;
        mOrdersArray = arrayOrders;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order, parent, false);
        return new OrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        Order orderObject = mOrdersArray.get(position);

        holder.id.setText(String.valueOf(orderObject.id));
        holder.date.setText(orderObject.date);

    }

    @Override
    public int getItemCount() {
        return mOrdersArray.size();
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

    public class OrdersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView card;
        TextView id, date;

        OrdersViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            id = (TextView) itemView.findViewById(R.id.id);
            date = (TextView) itemView.findViewById(R.id.date);
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
