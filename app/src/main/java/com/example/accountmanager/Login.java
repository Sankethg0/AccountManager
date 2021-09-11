package com.example.accountmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://account-manager-76ba4-default-rtdb.firebaseio.com/users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         EditText email=findViewById(R.id.email);
         EditText password=findViewById(R.id.password);
         Button loginBTN=findViewById(R.id.signupBTN);

         loginBTN.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String emailTxt=email.getText().toString();
                 String passTxt=password.getText().toString();

                 if(emailTxt.isEmpty()||passTxt.isEmpty()){
                     Toast.makeText(Login.this,"Please enter login Details",Toast.LENGTH_SHORT).show();

                 }else {
                     databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot snapshot) {
                             //checks if the username exist in the database
                             if (snapshot.hasChild(emailTxt)) {
                                //username exist in the database and now get the password
                                 String getPassword=snapshot.child(emailTxt).child("password").getValue(String.class);
                                 if(getPassword.equals(passTxt)){
                                     Toast.makeText(Login.this,"Successfully logged on",Toast.LENGTH_SHORT).show();
                                     startActivity(new Intent(getApplicationContext(),Dashboard.class));
                                     finish();
                                 }else{
                                     Toast.makeText(Login.this,"Incorrect password",Toast.LENGTH_SHORT).show();
                                 }
                             }else{
                                 Toast.makeText(Login.this,"Incorrect Password",Toast.LENGTH_SHORT).show();
                             }


                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError error) {

                         }
                     });
                 }
             }
         });
       

    }

    public void gotoRegister(View view) {
        startActivity(new Intent(getApplicationContext(),Register.class));
        finish();
    }
}