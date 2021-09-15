package com.tecdehcode.week1_0706012010050;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Model.User;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton main_floatingActionButton;
    private RecyclerView main_recyclerView;
    private TextView textViewNodata;
    public static ArrayList<User> dataUser;
    static Adapter adapter;

    boolean doubleTapExit = false;

    @Override
    public void onBackPressed(){
        if(doubleTapExit) {
            super.onBackPressed();
            return;
        }

        this.doubleTapExit = true;
        Toast.makeText(this, "Tap Again to Exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleTapExit = false;
            }
        }, 2000);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        main_recyclerView = findViewById(R.id.main_recyclerView);
        main_floatingActionButton = findViewById(R.id.main_floatingActionButton);
        textViewNodata = findViewById(R.id.textViewNodata);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main);
        initView();
        setupRecyclerView();
        setListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == 200){
                User dataBaru = data.getParcelableExtra("listuser");
                dataUser.add(dataBaru);
                adapter.notifyDataSetChanged();
                textViewNodata.setVisibility(View.GONE);
            }
        }
    }


    private void setListener() {
        main_floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getBaseContext(), mainAdd.class);
                startActivityForResult(intent,1);
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        main_recyclerView.setLayoutManager(manager);
        main_recyclerView.setAdapter(adapter);
    }

    private void initView() {
        main_recyclerView = findViewById(R.id.main_recyclerView);
        dataUser = new ArrayList<User>();
        adapter = new Adapter(dataUser);
        main_floatingActionButton = findViewById(R.id.main_floatingActionButton);
        textViewNodata = findViewById(R.id.textViewNodata);
    }
}