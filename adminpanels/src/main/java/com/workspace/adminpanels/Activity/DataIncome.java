package com.workspace.adminpanels.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.workspace.adminpanels.Adapter.incomeAdapter;
import com.workspace.adminpanels.Model.IncomeModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;

public class DataIncome extends AppCompatActivity {

    private Toolbar incomeToolbar;
    private RecyclerView rvIncome;
    private TextView textTotal;
    DatabaseReference incomeRef;
    ArrayList<IncomeModel> iList;
    incomeAdapter adapter;
    int totalIncome = 0 ;
    String totalHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_income);

        incomeToolbar = findViewById(R.id.toolbar_data_income);
        textTotal = findViewById(R.id.text_total_income_all);
        rvIncome = findViewById(R.id.rvd_income);
        rvIncome.setHasFixedSize(true);
        rvIncome.setLayoutManager(new LinearLayoutManager(this));
        iList = new ArrayList<>();

        incomeRef = FirebaseDatabase.getInstance().getReference("Data").child("Transaksi");
        incomeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String key = ds.getKey();
                    for (DataSnapshot dSnap : dataSnapshot.child(key).child("Pesanan").getChildren()){
                        String keys = dSnap.getKey();
                        for (DataSnapshot ds1 : dataSnapshot.child(key).child("Pesanan").child(keys).getChildren()){
                            IncomeModel iMods = ds1.getValue(IncomeModel.class);
                            iList.add(iMods);
                            adapter = new incomeAdapter(iList);
                            rvIncome.setAdapter(adapter);
                        }
                    }
                    for (int i = 0; i < iList.size(); i++){
                        totalIncome += iList.get(i).getTotal();
                        totalHarga = Integer.toString(totalIncome);
                        Log.d("TAG", String.valueOf(iList.size()));
                    }
                    textTotal.setText(totalHarga);
                }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}