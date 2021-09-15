package com.tecdehcode.week1_0706012010050;

import static com.tecdehcode.week1_0706012010050.MainActivity.adapter;
import static com.tecdehcode.week1_0706012010050.MainActivity.dataUser;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import Model.User;

public class mainAdd extends AppCompatActivity {
    private TextInputLayout textInputLayout_nama, textInputLayout_umur, textInputLayout_alamat;
    private Button button_save;
    private TextView titleBar;
    private ImageView image_back;
    Loading loading = new Loading(mainAdd.this);
//    private ArrayList<User> arrayUser = dataUser;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_add_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        initView();
//        add();

//        Bundle bundle = getIntent().getExtras();
//
//        String kondisi = bundle.getString("kondisi");
//        if(kondisi.equalsIgnoreCase("add")){
//            add();
//        }else if(kondisi.equalsIgnoreCase("edit")){
//            edit();
//        }

        textInputLayout_nama.getEditText().addTextChangedListener(textWatch);
        textInputLayout_umur.getEditText().addTextChangedListener(textWatch);
        textInputLayout_alamat.getEditText().addTextChangedListener(textWatch);

        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    private TextWatcher textWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String animeTitle = textInputLayout_nama.getEditText().getText().toString().trim();
            String jumlahEpisode = textInputLayout_umur.getEditText().getText().toString().trim();
            String penulis = textInputLayout_alamat.getEditText().getText().toString().trim();

            if (!animeTitle.isEmpty() && animeTitle.length() < 21) {
                if (!jumlahEpisode.isEmpty() && !penulis.isEmpty()) {
                    button_save.setEnabled(true);
                } else {
                    button_save.setEnabled(false);
                }
            } else {
                textInputLayout_nama.setError("Maksimal 20 Karakter");
                button_save.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void add() {
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama= textInputLayout_nama.getEditText().getText().toString().trim();
                String umur = textInputLayout_umur.getEditText().getText().toString().trim();
                String alamat = textInputLayout_alamat.getEditText().getText().toString().trim();

                User user = new User(nama, umur, alamat);
                Intent intent = new Intent();
                intent.putExtra("listuser", user);

                setResult(200, intent);
                loading.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading.dismissDialog();
                        finish();
                    }
                }, 1000);


                Toast.makeText(mainAdd.this, "Data Telah Disimpan!", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initView() {
        image_back = findViewById(R.id.image_back);
        button_save = findViewById(R.id.button_save);
        textInputLayout_nama = findViewById(R.id.textInputLayout_nama);
        textInputLayout_alamat = findViewById(R.id.textInputLayout_alamat);
        textInputLayout_umur = findViewById(R.id.textInputLayout_umur);
        titleBar = findViewById(R.id.titleBar);
        Intent i = getIntent();
        int kondisi = i.getIntExtra("select",0);
        if(kondisi == 2){
            edit();
        }else{
            add();
        }

    }

        private void edit(){
            Intent intent = getIntent();
            int pos = intent.getIntExtra("data",0); //deklarasi awal
            User user = intent.getParcelableExtra("baru");
            textInputLayout_nama.getEditText().setText(user.getNama());
            textInputLayout_alamat.getEditText().setText(user.getAlamat());
            textInputLayout_umur.getEditText().setText(user.getUmur());

            titleBar.setText("Name Edit");

            button_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nama= textInputLayout_nama.getEditText().getText().toString().trim();
                    String umur = textInputLayout_umur.getEditText().getText().toString().trim();
                    String alamat = textInputLayout_alamat.getEditText().getText().toString().trim();

                    loading.startLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            User user = new User(nama, umur, alamat);
                            dataUser.set(pos,user);
                            adapter.notifyDataSetChanged();
                            loading.dismissDialog();
                            finish();
                        }
                    }, 1000);


                    Toast.makeText(mainAdd.this, "Data Telah Diperbarui!", Toast.LENGTH_LONG).show();
                }
            });
    }
}

