package com.workspace.adminpanels.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.workspace.adminpanels.Model.menuModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class editDataMenu extends AppCompatActivity {

    private ImageView imgView;
    private EditText textNamaPaket, textDescPaket, textHargaPaket, textMinPaket;
    private Button btnSave;
    ArrayList<menuModel> menuList;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_menu);
        //initial
        init();
        parceableSet();
        saveClick();
    }

    private void saveClick() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuList = new ArrayList<>();
                database = FirebaseDatabase.getInstance().getReference("Data").child("Menu");
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds:dataSnapshot.getChildren()){
                            String value = ds.getKey();

                            String names = textNamaPaket.getText().toString().trim();
                            String descp = textDescPaket.getText().toString().trim();
                            Integer harga = Integer.valueOf(textHargaPaket.getText().toString().trim());
                            Integer minim = Integer.valueOf(textMinPaket.getText().toString().trim());

                            menuModel menumods = new menuModel(names, descp, harga, minim);
                            database.setValue(menumods);

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    private void parceableSet() {
        Intent intent = getIntent();
        menuModel menuMods = intent.getParcelableExtra("judul");
        final String judul = Objects.requireNonNull(menuMods).getJudul();
        textNamaPaket.setText(judul);
        String desc = menuMods.getDesc();
        textDescPaket.setText(desc);
        String harga = String.valueOf(menuMods.getHarga());
        textHargaPaket.setText(harga);
        String minim = String.valueOf(menuMods.getMinimal());
        textMinPaket.setText(minim);
        String gambar = menuMods.getGambar();
        Picasso.get().load(gambar).into(imgView);
    }

    private void init() {
    textNamaPaket = findViewById(R.id.edit_nama_paket);
    textDescPaket = findViewById(R.id.edit_desc_paket);
    textHargaPaket = findViewById(R.id.edit_harga_paket);
    textMinPaket = findViewById(R.id.edit_minimum_paket);
    imgView = findViewById(R.id.imgViewEdit);
    btnSave = findViewById(R.id.btn_save_edit);
    }
}