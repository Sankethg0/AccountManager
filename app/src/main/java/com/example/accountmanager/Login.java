package com.example.accountmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    FirebaseAuth fAuth;
    Button loginBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         final EditText email=findViewById(R.id.email);
         final EditText password=findViewById(R.id.password);
         loginBTN=findViewById(R.id.loginBTN);
         fAuth=FirebaseAuth.getInstance();

         loginBTN.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 final String emailTxt=email.getText().toString();
                 final String passTxt=password.getText().toString();

                 if(emailTxt.isEmpty()||passTxt.isEmpty()){
                     Toast.makeText(Login.this,"Please enter login Details",Toast.LENGTH_SHORT).show();

                 }else {
                     fAuth.signInWithEmailAndPassword(emailTxt,passTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if(task.isSuccessful()){
                                 Toast.makeText(Login.this,"Successfully Logged In.",Toast.LENGTH_SHORT).show();
                                 startActivity(new Intent(getApplicationContext(),Dashboard.class));
                             }else {
                                 Toast.makeText(Login.this,"Failed to Log In."+task.getException(),Toast.LENGTH_SHORT).show();
                             }
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