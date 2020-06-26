package com.workspace.adminpanels.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.workspace.adminpanels.R;

public class DataPesanan extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pesanan);

        mToolbar = findViewById(R.id.toolbarPesanan);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Pesanan");

    }
}