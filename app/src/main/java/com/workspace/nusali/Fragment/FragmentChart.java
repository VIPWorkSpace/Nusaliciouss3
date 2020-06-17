package com.workspace.nusali.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.workspace.nusali.Adapter.ChartAdapter;
import com.workspace.nusali.Model.ChartModel;
import com.workspace.nusali.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class FragmentChart extends Fragment {
    private String userIdKey = "";
    private String userId = "";
    DatabaseReference referenceChart;
    RecyclerView recyclerViewChart;
    ArrayList<ChartModel> chartList;
    ChartAdapter chartAdapter;

    public FragmentChart() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_chart, container, false);

        getUsernameLocal();
        recyclerViewChart = v.findViewById(R.id.CheckoutRecycler);
        recyclerViewChart.setHasFixedSize(true);
        recyclerViewChart.setLayoutManager(new LinearLayoutManager(getContext()));
        chartList = new ArrayList<>();

        referenceChart = FirebaseDatabase.getInstance().getReference().child("Keranjang").child(userId);
        referenceChart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ChartModel chartModel = dataSnapshot1.getValue(ChartModel.class);
                    chartList.add(chartModel);
                }
                chartAdapter = new ChartAdapter(getActivity(), chartList);
                recyclerViewChart.setAdapter(chartAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return v;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(userIdKey, MODE_PRIVATE);
        userId = sharedPreferences.getString("firebaseKey", "");

    }
}