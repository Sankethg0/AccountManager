package com.example.accountmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class addAcc extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://account-manager-76ba4-default-rtdb.firebaseio.com/");
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_acc);

        EditText accName=findViewById(R.id.nameAcc);
        EditText accEmail=findViewById(R.id.accEmail);
        EditText accPass=findViewById(R.id.accPass);
        Button addAccBTN=findViewById(R.id.addAccBTN);

        fAuth=FirebaseAuth.getInstance();

        addAccBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accNme=accName.getText().toString();
                String accPwsrd=accPass.getText().toString();
                String accMail=accEmail.getText().toString();

                if(accNme.isEmpty()||accPwsrd.isEmpty()||accMail.isEmpty()){
                    Toast.makeText(addAcc.this,"Fill all the details",Toast.LENGTH_SHORT).show();
                }else{
                    String userId=fAuth.getCurrentUser().getUid();
                    DatabaseReference currentUserDb=FirebaseDatabase.getInstance().getReference().child("accounts").push();
                    Map userInfo=new HashMap<>();
                    userInfo.put("UserId",userId);
                    userInfo.put("Name of Account",accNme);
                    userInfo.put("Email of Account",accMail);
                    userInfo.put("Password of Account",accPwsrd);
                    currentUserDb.updateChildren(userInfo);
                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                    finish();
                }
            }
        });
    }
}