package com.workspace.adminpanels.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.workspace.adminpanels.Adapter.userData;
import com.workspace.adminpanels.Model.userModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;

public class DataUserActivity extends AppCompatActivity {

    private Toolbar dToolbar;
    private RecyclerView rvdUser;
    private SearchView svdUser;
    private DatabaseReference dataRef;
    private userData userAdapter;
    private ArrayList<userModel> userModels;
    private SwipeRefreshLayout srlSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user);

        rvdUser = findViewById(R.id.rvd_user);
        svdUser = findViewById(R.id.searchData);
        dToolbar = findViewById(R.id.dToolbar);
        setSupportActionBar(dToolbar);
        getSupportActionBar().setTitle("Data User");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvdUser.setHasFixedSize(true);
        rvdUser.setLayoutManager(new LinearLayoutManager(this));
        userModels = new ArrayList<userModel>();
        retrieveData();
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvdUser.addItemDecoration(divider);
    }
    private void retrieveData(){
        dataRef = FirebaseDatabase.getInstance().getReference().child("User");
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    userModel userMod = dataSnapshot1.getValue(userModel.class);
                    userModels.add(userMod);
                }
                userAdapter = new userData(userModels);
                rvdUser.setAdapter(userAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        searchData();
    }
    private void searchData(){
        dataRef = FirebaseDatabase.getInstance().getReference().child("User");
        svdUser.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<userModel> mList = new ArrayList<>();
                for (userModel object : userModels){
                    if (object.getName().toLowerCase().contains(newText.toLowerCase())){
                        mList.add(object);
                    } else if (object.getPhone().toLowerCase().contains(newText.toLowerCase())){
                        mList.add(object);
                    }
                }
                userData usrData = new userData(mList);
                rvdUser.setAdapter(usrData);

                return true;
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}