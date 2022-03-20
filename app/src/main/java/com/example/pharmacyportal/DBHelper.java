package com.example.pharmacyportal;

import static com.example.pharmacyportal.DbBitmapUtility.getBitmap;
import static com.example.pharmacyportal.DbBitmapUtility.getBytes;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    public static int userId = -1;
    public Context mContext;

    public DBHelper(Context context) {
        super(context, "pmsp.db", null, 1);

        this.mContext = context;

        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Medicine", null);

        if (cursor.getCount() <= 0) {

            Bitmap bit_image_1 = getBitmap(this.mContext, "images/1.jpeg");
            insertMedicineData(1, "Cipmol 500 mg Tablet 10's", "PARACETAMOL-500MG", 11, "CIPLA LTD", "Cipmol 500 mg Tablet 10's belongs to the group of mild analgesics (pain killer), and antipyretic (fever-reducing agent) used to treat mild to moderate pain including headache, migraine, toothache, menstrual period pain, osteoarthritis pain, musculoskeletal pain, and reducing fever. Pain and fever are caused by the activation of pain receptors due to the release of certain natural chemicals in our body like prostaglandin. \n", bit_image_1);

            Bitmap bit_image_2 = getBitmap(this.mContext, "images/2.jpeg");
            insertMedicineData(2, "Calpol 500mg Tablet 15's", "PARACETAMOL-500MG", 15, "GLAXOSMITHKLINE PHARMACEUTICALS LTD", "Calpol 500mg Tablet 15's belongs to the group of mild analgesics (pain killer), and antipyretic (fever-reducing agent) used to treat mild to moderate pain including headache, migraine, toothache, menstrual period pain, osteoarthritis pain, musculoskeletal pain, and reducing fever. Pain and fever are caused by the activation of pain receptors due to the release of certain natural chemicals in our body like prostaglandin.\n", bit_image_2);

            Bitmap bit_image_3 = getBitmap(this.mContext, "images/3.jpeg");
            insertMedicineData(3, "Azax-500 Tablet 3's", "AZITHROMYCIN-500MG", 71.25, "SUN PHARMACEUTICAL INDUSTRIES LTD (RANBAXY)", "Azax-500 Tablet 3's belongs to a group of medicines known as macrolide antibiotics. It is used to treat various bacterial infections of the respiratory system (like pneumonia, bronchitis, tonsillitis, pharyngitis and sinusitis), skin infections (like acne and rosacea), ear infections, and sexually transmitted infections. A bacterial infection is a condition in which bacteria grows in the body and cause infection. It can target any body part and multiple very quickly.\n", bit_image_3);

            Bitmap bit_image_4 = getBitmap(this.mContext, "images/4.jpeg");
            insertMedicineData(4, "Azee-500 Tablet 5's", "AZITHROMYCIN-500MG", 119.5, "CIPLA LTD", "Azee-500 Tablet 5's belongs to a group of medicines known as macrolide antibiotics. It is used to treat various bacterial infections of the respiratory system (like pneumonia, bronchitis, tonsillitis, pharyngitis and sinusitis), skin infections (like acne and rosacea), ear infections, and sexually transmitted infections. A bacterial infection is a condition in which bacteria grows in the body and cause infection. It can target any body part and multiple very quickly.\n", bit_image_4);

            Bitmap bit_image_5 = getBitmap(this.mContext, "images/5.jpeg");
            insertMedicineData(5, "Azithral-500 Tablet 5's", "AZITHROMYCIN-500MG", 119.5, "ALEMBIC PHARMACEUTICALS LTD", "Azithral-500 Tablet 5's belongs to a group of medicines known as macrolide antibiotics. It is used to treat various bacterial infections of the respiratory system (like pneumonia, bronchitis, tonsillitis, pharyngitis and sinusitis), skin infections (like acne and rosacea), ear infections, and sexually transmitted infections. A bacterial infection is a condition in which bacteria grows in the body and cause infection. It can target any body part and multiple very quickly.\n", bit_image_5);

        }
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Customer  (\n" +
                "    id          INTEGER         PRIMARY KEY AUTOINCREMENT NOT NULL, \n" +
                "    username    VARCHAR(30)     UNIQUE NOT NULL,\n" +
                "    password    VARCHAR(30)     NOT NULL,\n" +
                "    email       TEXT            UNIQUE NOT NULL,\n" +
                "    firstName   TEXT            NOT NULL,\n" +
                "    lastName    TEXT            NOT NULL,\n" +
                "    dateOfBirth TEXT            NOT NULL,\n" +
                "    aptNumber   TEXT            NOT NULL,\n" +
                "    streetName  TEXT            NOT NULL,\n" +
                "    city        TEXT            NOT NULL,\n" +
                "    state       TEXT            NOT NULL,\n" +
                "    country     TEXT            NOT NULL,\n" +
                "    zip         CHARACTER(6)    NOT NULL,\n" +
                "    phone       CHARACTER(10)   NOT NULL\n" +
                ")"
        );

        DB.execSQL("CREATE TABLE Medicine (\n" +
                "    id                  INTEGER          PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "    tradeName           TEXT            NOT NULL,\n" +
                "    scientificName      TEXT            NOT NULL,\n" +
                "    sellingPrice        DECIMAL(10,3)   NOT NULL,\n" +
                "    manufacturerName    TEXT            NOT NULL,\n" +
                "    description         TEXT            NOT NULL,\n" +
                "    image               BLOB                    \n" +
                ")");

        DB.execSQL("CREATE TABLE Rating (\n" +
                "    id          INTEGER          PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "    medicineId  INTEGER          NOT NULL,\n" +
                "    customerId  INTEGER          NOT NULL,\n" +
                "    rating      DECIMAL(1,3)     NOT NULL,\n" +
                "    CONSTRAINT fk_medicines\n" +
                "        FOREIGN KEY (medicineId)\n" +
                "        REFERENCES Medicine(id)\n" +
                "        ON DELETE CASCADE,\n" +
                "    CONSTRAINT fk_customers\n" +
                "        FOREIGN KEY (customerId)\n" +
                "        REFERENCES Customer(id)\n" +
                "        ON DELETE CASCADE,\n" +
                "    UNIQUE(medicineId, customerId) ON CONFLICT REPLACE\n" +
                ")");

        DB.execSQL("CREATE TABLE CustomerOrder (\n" +
                "    id              INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "    customerId      INTEGER      NOT NULL,\n" +
                "    orderDate       DATE        NOT NULL,\n" +
                "    placed          BOOLEAN     NOT NULL,\n" +
                "    paymentMethod   TEXT        NOT NULL,\n" +
                "    CONSTRAINT fk_customers\n" +
                "        FOREIGN KEY (customerId)\n" +
                "        REFERENCES Customer(id)\n" +
                "        ON DELETE CASCADE\n" +
                ")");

        DB.execSQL("CREATE TABLE Order_On_Medicine (\n" +
                "    id          INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "    orderId     INTEGER      NOT NULL,\n" +
                "    medicineId  INTEGER      NOT NULL,\n" +
                "    quantity    INT         NOT NULL,\n" +
                "    CONSTRAINT fk_medicines\n" +
                "        FOREIGN KEY (medicineId)\n" +
                "        REFERENCES Medicine(id)\n" +
                "        ON DELETE CASCADE\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS Order_On_Medicine");
        DB.execSQL("DROP TABLE IF EXISTS CustomerOrder");
        DB.execSQL("DROP TABLE IF EXISTS Rating");
        DB.execSQL("DROP TABLE IF EXISTS Medicine");
        DB.execSQL("DROP TABLE IF EXISTS Customer");
    }

    public boolean insertMedicineData(int id, String tradeName, String scientificName, double sellingPrice, String manufacturerName, String description, Bitmap bit_image) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("tradeName", tradeName);
        contentValues.put("scientificName", scientificName);
        contentValues.put("sellingPrice", sellingPrice);
        contentValues.put("manufacturerName", manufacturerName);
        contentValues.put("description", description);

        byte[] image = getBytes(bit_image);
        contentValues.put("image", image);

        long result = DB.insert("Medicine", null, contentValues);
        return result != -1;
    }

    public Cursor getAllMedicine() {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Medicine", null);
        return cursor;
    }

    public Cursor getMedicine(int id) {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Medicine WHERE id=?", new String[]{String.valueOf(id)});
        if (cursor.getCount() == 1) {
            cursor.moveToPosition(cursor.getCount() - 1);
            return cursor;
        } else {
            return null;
        }
    }

    public boolean insertCustomerData(String username, String password, String email, String firstname, String lastname, String dob, String aptNumber, String streetName, String city, String state, String country, String zip, String phone) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("firstName", firstname);
        contentValues.put("lastName", lastname);
        contentValues.put("dateOfBirth", dob);
        contentValues.put("aptNumber", aptNumber);
        contentValues.put("streetName", streetName);
        contentValues.put("city", city);
        contentValues.put("state", state);
        contentValues.put("country", country);
        contentValues.put("zip", zip);
        contentValues.put("phone", phone);

        long result = DB.insert("Customer", null, contentValues);
        return result != -1;
    }

    @SuppressLint("Range")
    public boolean authenticateUser(String username, String password) {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Customer WHERE username=? AND password=?", new String[]{username, password});
        if (cursor.getCount() == 1) {
            cursor.moveToPosition(cursor.getCount() - 1);
            DBHelper.userId = cursor.getInt(cursor.getColumnIndex("id"));
            return true;
        } else {
            return false;
        }
    }

    @SuppressLint("Range")
    private int createCart() {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("customerId", DBHelper.userId);

        @SuppressLint("SimpleDateFormat")
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        contentValues.put("orderDate", date);

        contentValues.put("placed", 0);
        contentValues.put("paymentMethod", "Cash On Delivery");

        long result = DB.insert("CustomerOrder", null, contentValues);

        Cursor cursor = DB.rawQuery("SELECT * FROM CustomerOrder WHERE placed=?", new String[]{String.valueOf(0)});

        if (cursor.getCount() == 1) {
            cursor.moveToPosition(cursor.getCount() - 1);
            return cursor.getInt(cursor.getColumnIndex("id"));
        } else {
            return -1;
        }
    }

    @SuppressLint("Range")
    private int getCart() {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM CustomerOrder WHERE placed=?", new String[]{String.valueOf(0)});

        if (cursor.getCount() == 1) {
            cursor.moveToPosition(cursor.getCount() - 1);
            return cursor.getInt(cursor.getColumnIndex("id"));
        } else if (cursor.getCount() == 0) {
            return createCart();
        } else {
            DB = this.getWritableDatabase();
            DB.delete("CustomerOrder", "customerId=? AND placed=?", new String[]{String.valueOf(DBHelper.userId), String.valueOf(0)});
            return getCart();
        }
    }

    @SuppressLint("Range")
    public boolean insertItemIntoCart(int medicineId, int quantity) {
        int cartId = getCart();

        if (cartId < 0) {
            return false;
        } else {
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor medicineCursor = DB.rawQuery("SELECT * FROM Order_On_Medicine WHERE orderId=? AND medicineId=?", new String[]{String.valueOf(cartId), String.valueOf(medicineId)});

            ContentValues contentValues = new ContentValues();


            long result;
            if (medicineCursor.getCount() == 1) {
                medicineCursor.moveToPosition(medicineCursor.getCount() - 1);

                contentValues.put("quantity", quantity + medicineCursor.getInt(medicineCursor.getColumnIndex("quantity")));

                result = DB.update("Order_On_Medicine", contentValues, "orderId=? AND medicineId=?", new String[]{String.valueOf(cartId), String.valueOf(medicineId)});
            } else if (medicineCursor.getCount() > 1) {
                DB.delete("Order_On_Medicine", "orderId=? AND medicineId=?", new String[]{String.valueOf(cartId), String.valueOf(medicineId)});
                return insertItemIntoCart(medicineId, quantity);
            } else {
                contentValues.put("orderId", cartId);
                contentValues.put("medicineId", medicineId);
                contentValues.put("quantity", quantity);

                result = DB.insert("Order_On_Medicine", null, contentValues);
            }

            return result != -1;
        }
    }

    @SuppressLint("Range")
    public float getCustomerRating(int medicineId) {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Rating WHERE customerId=? AND medicineId=?", new String[]{String.valueOf(DBHelper.userId), String.valueOf(medicineId)});
        if (cursor.getCount() == 1) {
            cursor.moveToPosition(cursor.getCount() - 1);
            return cursor.getFloat(cursor.getColumnIndex("rating"));
        } else {
            return 0;
        }
    }

    public boolean changeCustomerRating(int medicineId, float rating) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Rating WHERE customerId=? AND medicineId=?", new String[]{String.valueOf(DBHelper.userId), String.valueOf(medicineId)});
        ContentValues contentValues = new ContentValues();
        contentValues.put("rating", rating);
        long result;
        if (cursor.getCount() == 1) {
            cursor.moveToPosition(cursor.getCount() - 1);
            result = DB.update("Rating", contentValues, "customerId=? AND medicineId=?", new String[]{String.valueOf(DBHelper.userId), String.valueOf(medicineId)});

        } else if (cursor.getCount() == 0) {
            contentValues.put("customerId", DBHelper.userId);
            contentValues.put("medicineId", medicineId);
            result = DB.insert("Rating", null, contentValues);

        } else {
            return false;
        }
        return result != -1;
    }

    @SuppressLint("Range")
    public float calculateAvgRating(int medicineId) {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Rating WHERE medicineId=?", new String[]{String.valueOf(medicineId)});
        float avgRating = 0;
        float count = 0;
        while (cursor.moveToNext()) {
            avgRating += cursor.getFloat(cursor.getColumnIndex("rating"));
            count++;
        }

        if (count != 0) {
            return avgRating / count;
        } else {
            return 0;
        }

    }

    @SuppressLint("Range")
    public ArrayList<OrderMedicine> getCartArray() {
        SQLiteDatabase DB = this.getReadableDatabase();
        int cartId = getCart();

        Cursor cursor = DB.rawQuery("SELECT * FROM Order_On_Medicine WHERE orderId=?", new String[]{String.valueOf(cartId)});

        if (cursor.getCount() == 0) {
            return null;
        } else {
            ArrayList<OrderMedicine> medicinesInCart = new ArrayList<>();
            while (cursor.moveToNext()) {
                Cursor medicineCursor = DB.rawQuery("SELECT * FROM Medicine WHERE id=?", new String[]{String.valueOf(cursor.getInt(2))});
                if (medicineCursor.getCount() == 1) {
                    medicineCursor.moveToPosition(medicineCursor.getCount() - 1);
                    medicinesInCart.add(new OrderMedicine(cartId, cursor.getInt(2), medicineCursor.getString(medicineCursor.getColumnIndex("tradeName")), cursor.getInt(3), medicineCursor.getDouble(medicineCursor.getColumnIndex("sellingPrice"))));
                } else {
                    return null;
                }
            }
            return medicinesInCart;
        }
    }

    public void setPaymentMethod(String txt) {
        SQLiteDatabase DB = this.getWritableDatabase();
        int cartId = getCart();
        Cursor cursor = DB.rawQuery("SELECT * FROM CustomerOrder WHERE id=? AND customerId=?", new String[]{String.valueOf(cartId), String.valueOf(DBHelper.userId)});
        ContentValues contentValues = new ContentValues();
        contentValues.put("paymentMethod", txt);
        if (cursor.getCount() == 1) {
            long result = DB.update("CustomerOrder", contentValues, "id=? AND customerId=?", new String[]{String.valueOf(cartId), String.valueOf(DBHelper.userId)});
        }
    }

    @SuppressLint("Range")
    public int getCartId() {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM CustomerOrder WHERE placed=?", new String[]{String.valueOf(0)});
        if (cursor.getCount() == 1) {
            cursor.moveToPosition(cursor.getCount() - 1);
            return cursor.getInt(cursor.getColumnIndex("id"));
        } else if (cursor.getCount() == 0) {
            return -1;
        } else {
            DB = this.getWritableDatabase();
            DB.delete("CustomerOrder", "customerId=? AND placed=?", new String[]{String.valueOf(DBHelper.userId), String.valueOf(0)});
            return getCart();
        }
    }

    public void clearCart() {
        SQLiteDatabase DB = this.getWritableDatabase();
        int cartId = getCartId();
        if (cartId != -1) {
            DB.delete("CustomerOrder", "id=?", new String[]{String.valueOf(cartId)});
        }
    }

    public void placeOrder() {
        SQLiteDatabase DB = this.getWritableDatabase();
        int cartId = getCartId();
        ContentValues contentValues = new ContentValues();
        contentValues.put("placed", 1);
        if (cartId != -1) {
            DB.update("CustomerOrder", contentValues, "id=?", new String[]{String.valueOf(cartId)});
        }
    }

    @SuppressLint("Range")
    public ArrayList<Order> getAllOrders() {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM CustomerOrder WHERE customerId=? AND placed=?", new String[]{String.valueOf(DBHelper.userId), String.valueOf(1)});

        if (cursor.getCount() > 0) {
            ArrayList<Order> orderList = new ArrayList<>();
            while (cursor.moveToNext()) {
                orderList.add(new Order(cursor.getInt(0), cursor.getString(cursor.getColumnIndex("orderDate")), cursor.getString(cursor.getColumnIndex("paymentMethod"))));
            }
            return orderList;

        } else {
            return null;
        }
    }

    @SuppressLint("Range")
    public Order getOrder(int orderId) {
        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * FROM CustomerOrder WHERE customerId=? AND id=?", new String[]{String.valueOf(DBHelper.userId), String.valueOf(orderId)});

        if (cursor.getCount() == 0) {
            return null;
        } else {
            cursor.moveToPosition(cursor.getCount() - 1);
            return new Order(orderId, cursor.getString(2), cursor.getString(cursor.getColumnIndex("paymentMethod")));
        }
    }

    @SuppressLint("Range")
    public ArrayList<OrderMedicine> getOrderMedicine(int orderId) {
        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * FROM Order_On_Medicine WHERE orderId=?", new String[]{String.valueOf(orderId)});

        if (cursor.getCount() == 0) {
            return null;
        } else {
            ArrayList<OrderMedicine> medicinesInOrder = new ArrayList<>();
            while (cursor.moveToNext()) {
                Cursor medicineCursor = DB.rawQuery("SELECT * FROM Medicine WHERE id=?", new String[]{String.valueOf(cursor.getInt(2))});
                if (medicineCursor.getCount() == 1) {
                    medicineCursor.moveToPosition(medicineCursor.getCount() - 1);
                    medicinesInOrder.add(new OrderMedicine(orderId, cursor.getInt(2), medicineCursor.getString(medicineCursor.getColumnIndex("tradeName")), cursor.getInt(3), medicineCursor.getDouble(medicineCursor.getColumnIndex("sellingPrice"))));
                } else {
                    return null;
                }
            }
            return medicinesInOrder;
        }
    }

}
