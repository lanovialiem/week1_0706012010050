package com.tecdehcode.week1_0706012010050;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Model.User;

public class detailUser extends AppCompatActivity {

    private TextView detail_nama, detail_umur, detail_alamat;
    private ImageView image_edit, image_delete;
//    private ArrayList<User> arrayUser = MainActivity.dataUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailuser);
        getSupportActionBar().hide();


        final int pos = getIntent().getIntExtra("index", 0);
        detail_nama = findViewById(R.id.detail_nama);
        detail_alamat = findViewById(R.id.detail_alamat);
        detail_umur = findViewById(R.id.detail_umur);
        image_edit = findViewById(R.id.image_edit);
        image_delete = findViewById(R.id.image_delete);


        Intent intent = getIntent();
        User user = intent.getParcelableExtra("data");
        detail_nama.setText(user.getNama());
        detail_alamat.setText(user.getAlamat());
        detail_umur.setText(user.getUmur());



        image_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaBaru = detail_nama.getText().toString().trim();
                String umurBaru = detail_umur.getText().toString().trim();
                String alamatBaru = detail_alamat.getText().toString().trim();

                User user1 = new User(namaBaru, umurBaru, alamatBaru);
                Intent intent1 = new Intent(getBaseContext(), mainAdd.class );
                intent1.putExtra("baru", user1);
                intent1.putExtra("select",2);
                intent1.putExtra("data",pos);
                startActivity(intent1);
                finish();

            }
        });

        image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            MainActivity.dataUser.remove(pos);
            MainActivity.adapter.notifyDataSetChanged();
            finish();

            }
        });

    }
}
