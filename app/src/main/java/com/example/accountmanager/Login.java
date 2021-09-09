package com.example.accountmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email=findViewById(R.id.email);
        final EditText password=findViewById(R.id.password);
        final Button loginBTN=findViewById(R.id.signupBTN);

       

    }

    public void gotoRegister(View view) {
        startActivity(new Intent(getApplicationContext(),Register.class));
        finish();
    }
}