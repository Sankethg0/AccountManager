package com.example.accountmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdapter extends FirebaseRecyclerAdapter<Account,MyAdapter.accountViewholder>{
    public MyAdapter(
            @NonNull FirebaseRecyclerOptions<Account> options)
    {
        super(options);
    }

    @Override
    protected void
    onBindViewHolder(@NonNull accountViewholder holder,
                     int position, @NonNull Account model) {
        holder.account.setText(model.getAccName());
        holder.email.setText(model.getAccEmail());
        holder.password.setText(model.getAccPass());
    }
    @NonNull
    @Override
    public accountViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyAdapter.accountViewholder(view);
    }
    class accountViewholder extends RecyclerView.ViewHolder {
        TextView account, email, password;
        public accountViewholder(@NonNull View itemView)
        {
            super(itemView);

            account = itemView.findViewById(R.id.accName);
            email = itemView.findViewById(R.id.accMail);
            password = itemView.findViewById(R.id.accPassword);
        }
    }
}

