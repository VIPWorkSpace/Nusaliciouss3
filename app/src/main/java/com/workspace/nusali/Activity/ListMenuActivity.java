package com.workspace.nusali.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.workspace.nusali.Adapter.ListMenuAdapter;
import com.workspace.nusali.Model.ListMenuModel;
import com.workspace.nusali.R;

import java.util.ArrayList;

public class ListMenuActivity extends AppCompatActivity {

    RecyclerView recyclerListMenu;
    DatabaseReference referenceListMenu;
    ArrayList<ListMenuModel> listMenu;
    ListMenuAdapter listMenuAdapter;

    String userIdKey = "";
    String userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);

        recyclerListMenu = findViewById(R.id.recycler_list_menu);
        recyclerListMenu.setHasFixedSize(true);
        recyclerListMenu.setLayoutManager(new LinearLayoutManager(this));
        listMenu = new ArrayList<ListMenuModel>();

        referenceListMenu = FirebaseDatabase.getInstance().getReference().child("Data").child("Menu").child("Naskot");
        referenceListMenu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    ListMenuModel Listmenu = ds.getValue(ListMenuModel.class);
                    listMenu.add(Listmenu);
                }
                listMenuAdapter = new ListMenuAdapter(ListMenuActivity.this, listMenu);
                recyclerListMenu.setAdapter(listMenuAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}