package com.example.pharmacyportal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DbBitmapUtility {

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public static Bitmap getBitmap(Context ctx, String pathNameRelativeToAssetsFolder) {
        InputStream bitmapIs = null;
        Bitmap bmp = null;
        try {
            bitmapIs = ctx.getAssets().open(pathNameRelativeToAssetsFolder);
            bmp = BitmapFactory.decodeStream(bitmapIs);
        } catch (IOException e) {
            // Error reading the file
            e.printStackTrace();

        } finally {
            if (bitmapIs != null) {
                try {
                    bitmapIs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return bmp;
    }
}
