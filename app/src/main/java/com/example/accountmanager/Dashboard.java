package com.example.accountmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    private RecyclerView recyclerView;
    MyAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    // Firebase Realtime Database
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Create a instance of the database and get
        // its reference
        fAuth=FirebaseAuth.getInstance();
        String userId=fAuth.getCurrentUser().getUid();
        mbase = FirebaseDatabase.getInstance().getReference().child("accounts").child(userId);

        recyclerView = findViewById(R.id.userList);
        recyclerView.setHasFixedSize(true);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<Account> options
                = new FirebaseRecyclerOptions.Builder<Account>()
                .setQuery(mbase, Account.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new MyAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

    public void gotoAddAcc(View view) {
        startActivity(new Intent(getApplicationContext(),addAcc.class));
        finish();
    }
}