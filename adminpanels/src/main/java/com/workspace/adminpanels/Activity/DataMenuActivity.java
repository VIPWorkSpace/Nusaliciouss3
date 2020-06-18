package com.workspace.adminpanels.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.workspace.adminpanels.Adapter.menuAdapter;
import com.workspace.adminpanels.Model.menuModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;

public class DataMenuActivity extends AppCompatActivity {

    Toolbar toolbarMenu;
    SearchView searchMenu;
    RecyclerView rvdMenu;
    FloatingActionButton fabMenu;
    DatabaseReference menuRef;
    ArrayList<menuModel> menuList;
    menuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_menu);

        toolbarMenu = findViewById(R.id.dToolbarMenu);
        setSupportActionBar(toolbarMenu);
        getSupportActionBar().setTitle("Menu");
        searchMenu = findViewById(R.id.searchMenu);
        fabMenu = findViewById(R.id.fab_addMenu);
        rvdMenu = findViewById(R.id.rvd_Menu);
        rvdMenu.setHasFixedSize(true);
        rvdMenu.setLayoutManager(new LinearLayoutManager(this));

        menuList = new ArrayList<menuModel>();
        retrieveData();
        fabListener();
    }
    private void retrieveData(){
        menuRef = FirebaseDatabase.getInstance().getReference().child("DataMenu");
        menuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    menuModel menuMod = ds.getValue(menuModel.class);
                        menuList.add(menuMod);
                }
                adapter = new menuAdapter(menuList);
                rvdMenu.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void fabListener(){
        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(DataMenuActivity.this, DataAddMenuActivity.class);
                startActivity(add);
            }
        });
    }
}