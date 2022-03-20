package com.example.pharmacyportal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    EditText username, password, email, firstname, lastname, dob, aptNumber, streetName, city, state, country, zip, phone;
    TextView login;
    Button register;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        firstname = findViewById(R.id.firstName);
        lastname = findViewById(R.id.lastName);
        dob = findViewById(R.id.dob);
        aptNumber = findViewById(R.id.aptNumber);
        streetName = findViewById(R.id.streetName);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        country = findViewById(R.id.country);
        zip = findViewById(R.id.zip);
        phone = findViewById(R.id.phone);

        register = findViewById(R.id.register);

        DB = new DBHelper(this);

        login = findViewById(R.id.clickLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(getApplicationContext(), Login.class);
                startActivity(loginIntent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameTXT = username.getText().toString();
                String passwordTXT = password.getText().toString();
                String emailTXT = email.getText().toString();
                String firstnameTXT = firstname.getText().toString();
                String lastnameTXT = lastname.getText().toString();
                String dobTXT = dob.getText().toString();
                String aptNumberTXT = aptNumber.getText().toString();
                String streetNameTXT = streetName.getText().toString();
                String cityTXT = city.getText().toString();
                String stateTXT = state.getText().toString();
                String countryTXT = country.getText().toString();
                String zipTXT = zip.getText().toString();
                String phoneTXT = phone.getText().toString();


                boolean checkInsertData = DB.insertCustomerData(usernameTXT, passwordTXT, emailTXT, firstnameTXT, lastnameTXT, dobTXT, aptNumberTXT, streetNameTXT, cityTXT, stateTXT, countryTXT, zipTXT, phoneTXT);
                if (checkInsertData) {
                    Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                    Intent loginIntent = new Intent(getApplicationContext(), Login.class);
                    startActivity(loginIntent);
                    finish();

                } else {
                    Toast.makeText(Register.this, "Registration Unsuccessful. Try again.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}