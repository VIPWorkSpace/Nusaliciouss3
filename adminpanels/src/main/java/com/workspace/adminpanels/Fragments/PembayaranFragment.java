package com.workspace.adminpanels.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.workspace.adminpanels.Adapter.pembayaranAdapter;
import com.workspace.adminpanels.Model.pembayaranModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;

public class PembayaranFragment extends Fragment {

    RecyclerView rvPembayaran;
    ArrayList<pembayaranModel> pembayaranList;
    DatabaseReference dbRef;
//    DatabaseReference dbPembayaran = FirebaseDatabase.getInstance().getReference("Data");
//    DatabaseReference transRef = dbPembayaran.child("Transaksi");
    pembayaranAdapter adapter;

    public PembayaranFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pembayaran, container, false);

        rvPembayaran = v.findViewById(R.id.rvd_pembayaran);

        rvPembayaran.setHasFixedSize(true);
        rvPembayaran.setLayoutManager(new LinearLayoutManager(getContext()));
        pembayaranList = new ArrayList<>();
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rvPembayaran.addItemDecoration(divider);
        retrieveData();

//        ValueEventListener valueEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds : dataSnapshot.getChildren()){
//                    String mPembayaran = ds.getKey();
//                    Log.d("TAG", mPembayaran);
//
//                    for(DataSnapshot dSnapshot : dataSnapshot.child(mPembayaran).child("Pembayaran").getChildren()){
//                        String mPembayaran1 = dSnapshot.getKey();
//                        Log.d("TAG", "    " + mPembayaran1);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        };
//
//        transRef.addListenerForSingleValueEvent(valueEventListener);

        return v;
    }
    private void retrieveData(){
        dbRef = FirebaseDatabase.getInstance().getReference("Data").child("Transaksi");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String mPembayaran = ds.getKey();
                    Log.d("IND", mPembayaran);

                    for (DataSnapshot dSnap : dataSnapshot.child(mPembayaran).child("Pembayaran").getChildren()){
                        String mPembayaran1 = dSnap.getKey();
                        pembayaranModel pembayaranMod = dSnap.getValue(pembayaranModel.class);
                        pembayaranList.add(pembayaranMod);
                        Log.d("TAG", mPembayaran1);
                    }
                    adapter = new pembayaranAdapter(pembayaranList);
                    rvPembayaran.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}