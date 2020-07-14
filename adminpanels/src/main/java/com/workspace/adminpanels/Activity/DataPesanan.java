package com.workspace.adminpanels.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.workspace.adminpanels.Adapter.dataPesanAdapter;
import com.workspace.adminpanels.Model.dataPesanModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;
import java.util.List;

public class DataPesanan extends AppCompatActivity {

    public static final String EXTRA_UNIX = "extra_unix";
    private Toolbar toolbarPesan;
    private RecyclerView rvDataPesan;
    ArrayList<dataPesanModel> pesanModeList;
    dataPesanAdapter adapterPesanan;
    DatabaseReference pesananRef;
    public String uid;

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
        toolbarAction();
    }
    private void toolbarAction(){
        toolbarPesan.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbarPesan);
        getSupportActionBar().setTitle("PESANAN");
    }

    private void retrieveRecycler() {
    rvDataPesan.setHasFixedSize(true);
    rvDataPesan.setLayoutManager(new LinearLayoutManager(this));
    pesanModeList = new ArrayList<>();
        String unix = getIntent().getStringExtra(EXTRA_UNIX);
        uid = unix;
        pesananRef = FirebaseDatabase.getInstance().getReference("Data").child("Transaksi");
        pesananRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.child(uid).child("Pesanan").getChildren()){
                    String keym = ds.getKey();
                    for (DataSnapshot dsn : dataSnapshot.child(uid).child("Pesanan").child(keym).getChildren()){
                        dataPesanModel dataMod= dsn.getValue(dataPesanModel.class);
                        dataMod.key = keym;
                        pesanModeList.add(dataMod);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        adapterPesanan = new dataPesanAdapter(pesanModeList, this);
        rvDataPesan.setAdapter(adapterPesanan);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) item.getActionView();
//        searchView.setQueryHint("Input key");
//        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapterPesanan.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }
}