package com.example.pharmacyportal;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmacyportal.databinding.ActivityMedicineDetailBinding;

public class MedicineDetail extends DrawerBaseActivity {

    ActivityMedicineDetailBinding activityMedicineDetailBinding;

    TextView tradeName, scientificName, manufacturerName, description, price, avgRating;
    ImageView image;
    Button addToCart;
    DBHelper DB;
    Context mContext;
    RatingBar rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMedicineDetailBinding = ActivityMedicineDetailBinding.inflate(getLayoutInflater());
        setContentView(activityMedicineDetailBinding.getRoot());

        mContext = this;

        Bundle extras = getIntent().getExtras();
        int id;
        if (extras != null) {
            id = extras.getInt("id");
            //The key argument here must match that used in the other activity

            DB = new DBHelper(this);
            Cursor medicine = DB.getMedicine(id);

            if (medicine != null) {
                image = findViewById(R.id.medicineImage);
                image.setImageBitmap(DbBitmapUtility.getImage(medicine.getBlob(6)));

                tradeName = findViewById(R.id.tradeName);
                tradeName.setText(medicine.getString(1));

                scientificName = findViewById(R.id.scientificName);
                scientificName.setText(medicine.getString(2));

                manufacturerName = findViewById(R.id.manufacturerName);
                manufacturerName.setText(medicine.getString(4));

                description = findViewById(R.id.description);
                description.setText(medicine.getString(5));

                price = findViewById(R.id.price);
                String priceTXT = "\u20B9 " + medicine.getDouble(3);
                price.setText(priceTXT);

                avgRating = findViewById(R.id.avgRating);
                avgRating.setText(String.valueOf(DB.calculateAvgRating(id)));

                rate = findViewById(R.id.ratingBar);
                rate.setRating(DB.getCustomerRating(id));
                rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                        if (b) {
                            if (DB.changeCustomerRating(id, v)) {

                                Toast.makeText(mContext, "Rating Updated", Toast.LENGTH_SHORT).show();
                                avgRating.setText(String.valueOf(DB.calculateAvgRating(id)));
                            } else {
                                Toast.makeText(mContext, "Rating could not be updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

                addToCart = findViewById(R.id.addToCart);
                addToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean checkInsert = DB.insertItemIntoCart(id, 1);
                        if (checkInsert) {

                            Toast.makeText(mContext, "1 item added to cart", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, "Item could not be added to cart", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        }


    }
}