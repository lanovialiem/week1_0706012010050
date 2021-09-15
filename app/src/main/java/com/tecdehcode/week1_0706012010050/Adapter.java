package com.tecdehcode.week1_0706012010050;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Model.User;


public class Adapter  extends RecyclerView.Adapter<Adapter.UserViewHolder>{

    private ArrayList<User> listuser;

    public Adapter(ArrayList<User> listuser) {
        this.listuser = listuser;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview,parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.card_textView_nama.setText(listuser.get(position).getNama());
        holder.card_textView_alamat.setText(listuser.get(position).getAlamat());
        holder.card_textView_umur.setText(String.valueOf(listuser.get(position).getUmur()));


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context, mainAdd.class);
//                i.putExtra("position", position);
//                i.putExtra("kondisi", "edit");
//                context.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listuser.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView card_textView_nama, card_textView_alamat, card_textView_umur;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            card_textView_nama = itemView.findViewById(R.id.card_textView_nama);
            card_textView_umur = itemView.findViewById(R.id.card_textView_umur);
            card_textView_alamat = itemView.findViewById(R.id.card_textView_alamat);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nama = card_textView_nama.getText().toString().trim();
                    String umur =  card_textView_umur.getText().toString().trim();
                    String alamat = card_textView_alamat.getText().toString().trim();

                    Intent intent = new Intent(v.getContext(), detailUser.class);
                    User user = new User(nama, umur, alamat);
                    intent.putExtra("data", user);
                    intent.putExtra("index", getAdapterPosition());
                    v.getContext().startActivity(intent);
                }
            });

        }
    }

}

