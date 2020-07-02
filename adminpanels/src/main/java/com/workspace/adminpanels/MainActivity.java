package com.workspace.adminpanels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.workspace.adminpanels.Activity.DataAddMenuActivity;
import com.workspace.adminpanels.Activity.DataMenuActivity;
import com.workspace.adminpanels.Activity.DataTransaksi;
import com.workspace.adminpanels.Activity.DataUserActivity;
import com.workspace.adminpanels.Activity.adminLogin;
import com.workspace.adminpanels.Model.mainModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridDashboard;
    private TextView mUsername;
    private DatabaseReference dRef;
    ImageView logout;
    List<mainModel> mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = findViewById(R.id.textID);
        gridDashboard = findViewById(R.id.gridL);
        logout = findViewById(R.id.image_admin);
        setSingleEvent(gridDashboard);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent log = new Intent(MainActivity.this, adminLogin.class);
                startActivity(log);
            }
        });

        dRef = FirebaseDatabase.getInstance().getReference("Data").child("Admin");
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    String surenames = ds.getKey();
                    if (dataSnapshot.exists()){
                        mUsername.setText(dataSnapshot.child(surenames).child("surename").getValue().toString());
                    }
                    Log.d("NAME", surenames);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setSingleEvent(GridLayout gridDashboard) {
        for (int i = 0; i < gridDashboard.getChildCount(); i++) {
            CardView iCardview = (CardView) gridDashboard.getChildAt(i);
            final int finals = i;
            iCardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finals == 0) {
                        Intent dataMenu = new Intent(MainActivity.this, DataMenuActivity.class);
                        startActivity(dataMenu);
                    } else if (finals == 1) {
                        Intent addMenu = new Intent(MainActivity.this, DataAddMenuActivity.class);
                        startActivity(addMenu);
                    } else if (finals == 2) {
                        Intent dataUser = new Intent(MainActivity.this, DataUserActivity.class);
                        startActivity(dataUser);
                    } else if (finals == 3) {
                        Intent transaction = new Intent(MainActivity.this, DataTransaksi.class);
                        startActivity(transaction);
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
