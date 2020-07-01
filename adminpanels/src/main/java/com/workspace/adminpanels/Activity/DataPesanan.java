package com.workspace.adminpanels.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.workspace.adminpanels.Adapter.dataPesanAdapter;
import com.workspace.adminpanels.Model.dataPesanModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;

public class DataPesanan extends AppCompatActivity {

    private Toolbar toolbarPesan;
    private RecyclerView rvDataPesan;
    private SearchView svDataPesan;
    ArrayList<dataPesanModel> pesanModeList;
    dataPesanAdapter adapterPesanan;
    DatabaseReference pesananRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pesanan);

        init();
        retrieveRecycler();

    }

    private void init(){
        toolbarPesan = findViewById(R.id.toolbar_data_pesanan);
        rvDataPesan = findViewById(R.id.rvd_data_pesanan);
        svDataPesan = findViewById(R.id.search_data_pesanan);
        toolbarAction();
    }
    private void toolbarAction(){
        toolbarPesan.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        setSupportActionBar(toolbarPesan);
        getSupportActionBar().setTitle("PESANAN");
    }

    private void retrieveRecycler() {
    rvDataPesan.setHasFixedSize(true);
    rvDataPesan.setLayoutManager(new LinearLayoutManager(this));
    pesanModeList = new ArrayList<>();
    pesananRef = FirebaseDatabase.getInstance().getReference("Data").child("Transaksi");
    pesananRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot ds : dataSnapshot.getChildren()){
                String key = ds.getKey();
                for (DataSnapshot dSnap : dataSnapshot.child(key).child("Pesanan").getChildren()){
                    String keys = dSnap.getKey();
                    for (DataSnapshot ds1 : dataSnapshot.child(key).child("Pesanan").child(keys).getChildren()){
                        String Unix = ds1.getKey();
                        dataPesanModel dataMod = ds1.getValue(dataPesanModel.class);
                        pesanModeList.add(dataMod);
                    }
                }
            }
            adapterPesanan = new dataPesanAdapter(pesanModeList);
            rvDataPesan.setAdapter(adapterPesanan);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    }


}