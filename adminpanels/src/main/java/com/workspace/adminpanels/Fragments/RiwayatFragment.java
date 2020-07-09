package com.workspace.adminpanels.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
import com.workspace.adminpanels.Adapter.riwayatAdapter;
import com.workspace.adminpanels.Model.riwayat2Model;
import com.workspace.adminpanels.Model.riwayatModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;

public class RiwayatFragment extends Fragment {

    private RecyclerView rvRiwayat;
    private ArrayList<riwayat2Model> riwayatList;
    private riwayatAdapter adapter;
    private DatabaseReference riwayatRef;

    public RiwayatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_riwayat, container, false);

        recyclerSet(view);
        retrieveData();
        riwayatList = new ArrayList<>();
        return view;
    }

    private void retrieveData() {
    riwayatRef = FirebaseDatabase.getInstance().getReference("Data").child("Transaksi");
    riwayatRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                String uid = dataSnapshot1.getKey();
                for (DataSnapshot ds : dataSnapshot.child(uid).child("Riwayat").getChildren()){
                    String uniq = ds.getKey();
                    for (DataSnapshot dsn : dataSnapshot.child(uid).child("Riwayat").child(uniq).getChildren()){
                        String uniqKey = dsn.getKey();
                        Log.i("TAG", "onDataChange: "+uniqKey);
                        riwayat2Model riwayatMod = dsn.getValue(riwayat2Model.class);
                        riwayatList.add(riwayatMod);
                    }
                }
                adapter = new riwayatAdapter(riwayatList, getContext());
                rvRiwayat.setAdapter(adapter);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    }

    private void recyclerSet(View view) {
        rvRiwayat = view.findViewById(R.id.rvd_riwayat_pesanan);
        rvRiwayat.setHasFixedSize(true);
        rvRiwayat.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}