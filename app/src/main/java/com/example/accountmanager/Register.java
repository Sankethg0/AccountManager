package com.example.accountmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    //create object of the database reference class to access the firebase
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://account-manager-76ba4-default-rtdb.firebaseio.com/");
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText name=findViewById(R.id.name);
        EditText email=findViewById(R.id.email);
        EditText pass=findViewById(R.id.password);
        Button signupBTN=findViewById(R.id.signupBTN);

        fAuth=FirebaseAuth.getInstance();

        signupBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt=name.getText().toString();
                String passTxt=pass.getText().toString();
                String emailTxt=email.getText().toString();


                if(nameTxt.isEmpty()||passTxt.isEmpty()||emailTxt.isEmpty()){
                    Toast.makeText(Register.this,"Fill all the details",Toast.LENGTH_SHORT).show();
                }else{
                    fAuth.createUserWithEmailAndPassword(emailTxt,passTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Register.this,"Sign Up Error",Toast.LENGTH_SHORT);
                            }else{
                                String userId=fAuth.getCurrentUser().getUid();
                                DatabaseReference currentUserDb=FirebaseDatabase.getInstance().getReference().child("user").child(userId);
                                Map userInfo=new HashMap<>();
                                userInfo.put("email",emailTxt);
                                userInfo.put("password",passTxt);
                                currentUserDb.updateChildren(userInfo);
                                startActivity(new Intent(getApplicationContext(),Login.class));
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }

    public void gotoLogin(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}