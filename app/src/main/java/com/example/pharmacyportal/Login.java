package com.example.pharmacyportal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText username, password;
    TextView signUp;
    Button login;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);

        DB = new DBHelper(this);

        signUp = findViewById(R.id.clickSignUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(getApplicationContext(), Register.class);
                startActivity(registerIntent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameTXT = username.getText().toString();
                String passwordTXT = password.getText().toString();

                boolean checkInsertData = DB.authenticateUser(usernameTXT, passwordTXT);
                if (checkInsertData) {
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    Intent homeIntent = new Intent(getApplicationContext(), Home.class);
                    startActivity(homeIntent);
                    finish();

                } else {
                    Toast.makeText(Login.this, "Login Unsuccessful. Try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}