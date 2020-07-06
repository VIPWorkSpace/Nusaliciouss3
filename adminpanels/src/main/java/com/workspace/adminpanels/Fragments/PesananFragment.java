package com.workspace.adminpanels.Fragments;

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
import com.workspace.adminpanels.Adapter.pesananAdapter;
import com.workspace.adminpanels.Model.callbackidModel;
import com.workspace.adminpanels.Model.pesananModel;
import com.workspace.adminpanels.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PesananFragment extends Fragment {
    private callbackidModel callMod;
    private RecyclerView rvPesanan;
    private DatabaseReference dbPesanan;
    private pesananAdapter adapters;
    private ArrayList<callbackidModel> mCall;

    public PesananFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_pesanan, container, false);

        rvPesanan = v.findViewById(R.id.rvd_pesanan);
        rvPesanan.setHasFixedSize(true);
        rvPesanan.setLayoutManager(new LinearLayoutManager(getContext()));
        mCall = new ArrayList<>();

        dbPesanan = FirebaseDatabase.getInstance().getReference("Data").child("Transaksi");
        dbPesanan.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                     callMod = ds.getValue(callbackidModel.class);
                    callMod.key = ds.getKey();
                    mCall.add(callMod);
                }

                adapters = new pesananAdapter(mCall);
                rvPesanan.setAdapter(adapters);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return v;
    }
}