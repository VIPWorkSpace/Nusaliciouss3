package com.workspace.nusali.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.workspace.nusali.Activity.LoginActivity;
import com.workspace.nusali.Activity.PaymentActivity;
import com.workspace.nusali.Adapter.ChartAdapter;
import com.workspace.nusali.Model.ChartModel;
import com.workspace.nusali.Model.OrderModel;
import com.workspace.nusali.Model.PaymentModel;
import com.workspace.nusali.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;


public class FragmentChart extends Fragment {
    private String userIdKey = "";
    private String userId = "";
    TextView namaPenerima;
    DatabaseReference referenceChart;
    DatabaseReference referencePay;
    DatabaseReference referenceOrder;
    Task<Void> referenceRemove;
    RecyclerView recyclerViewChart;
    ArrayList<ChartModel> chartList;
    ChartAdapter chartAdapter;
    private Integer totalChart = 0;
    private Integer overTotal = 0;
    private Integer jumlahPesan = 0;
    Integer belanjaID = new Random().nextInt();
    String idTransaksi = belanjaID.toString();
    String toChart;

    public FragmentChart() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_chart, container, false);

        getUsernameLocal();

        //set button
        Button btnProses = v.findViewById(R.id.btn_proses_chart);
        namaPenerima = v.findViewById(R.id.nama_penerima);

        //set Recycler
        recyclerViewChart = v.findViewById(R.id.CheckoutRecycler);
        recyclerViewChart.setHasFixedSize(true);
        recyclerViewChart.setLayoutManager(new LinearLayoutManager(getContext()));
        chartList = new ArrayList<>();

        //LOAD RECYCLER KERANJANG
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


                for (int i = 0; i < chartList.size(); i++) {
//                    DataSnapshot snapshot = dataSnapshot.getChildren().iterator().next();
//                    ChartModel chartModel2 = snapshot.getValue(ChartModel.class);
                    totalChart = chartList.get(i).getTotal();
                    overTotal = overTotal + totalChart;
                    toChart = Integer.toString(overTotal);
                    TextView tvTotal = v.findViewById(R.id.tv_total_chart);
                    tvTotal.setText(toChart);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //PROSES PEMBAYARAN
        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToPayment();


            }
        });
        return v;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void goToPayment() {
//        if (TextUtils.isEmpty(namaPenerima.getText().toString())) {
//            Toast.makeText(getActivity(), "Tanggal Kosong ! ", Toast.LENGTH_SHORT).show();
//        } else {

        final String saveCurrentTime, saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();
        final SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        final SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        referencePay = FirebaseDatabase.getInstance().getReference().child("Transaksi").child(userId).child("Pembayaran");
        referencePay.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//

//                referencePay.getRef().child("idTransaksi").setValue(idTransaksi);
//                Integer totalBayar = Integer.parseInt(toChart);
//                referencePay.getRef().child("total").setValue(totalBayar);
//                referencePay.getRef().child("tanggal pesan").setValue(saveCurrentDate);
//                referencePay.getRef().child("waktu pesan").setValue(saveCurrentTime);
//                Intent intent = new Intent(getContext(), PaymentActivity.class);
//                startActivity(intent);

                Integer totalBayar = Integer.parseInt(toChart);
                Integer idTrans = Integer.parseInt(idTransaksi);
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ChartModel chartModel = dataSnapshot1.getValue(ChartModel.class);
                    chartList.add(chartModel);
                    for (int i = 0; i < chartList.size(); ) {
                        Integer jumlah = chartList.get(i++).getJumlah();
                        jumlahPesan = jumlahPesan + jumlah;
                        String katering = chartList.get(i).getKatering();
                        String tanggal = chartList.get(i).getTanggal();
                        String waktu = chartList.get(i).getWaktu();

                        PaymentModel payModel = new PaymentModel(idTrans, jumlahPesan, katering, tanggal, totalBayar, waktu, saveCurrentDate, saveCurrentTime);
                       referencePay.getRef().child(idTransaksi).setValue(payModel);
                        Intent intent = new Intent(getContext(), PaymentActivity.class);
                        startActivity(intent);


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

//            public void goToPesanan(){
//            referenceOrder = FirebaseDatabase.getInstance().getReference().child("Transaksi").child(userId).child("Pesanan");
//            referenceOrder.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                        ChartModel chartModel = dataSnapshot1.getValue(ChartModel.class);
//                        chartList.add(chartModel);
//                        for (int i = 0; i < chartList.size(); i++) {
//                                Integer idMenu= chartList.get(i).getId();
//                                String menuId = idMenu.toString();
//                                String judul = chartList.get(i).getJudul();
//                                Integer jumlah = chartList.get(i).getJumlah();
//                                String katering = chartList.get(i).getKatering();
//                                String tanggal = chartList.get(i).getTanggal();
//                                Integer total = chartList.get(i).getTotal();
//                                String waktu = chartList.get(i).getWaktu();
//
//                            OrderModel orderModel = new OrderModel(idMenu, judul, jumlah, katering, tanggal, total, waktu);
//                            referenceOrder.getRef().child(idTransaksi).child(menuId).setValue(orderModel).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//
//                                        referenceRemove = FirebaseDatabase.getInstance().getReference().child("Keranjang").child(userId).removeValue();
//                                        Toast.makeText(getContext(), "BAYAR BOS!", Toast.LENGTH_SHORT).show();
//
//
//
//                                }
//                            });
//                        }
//
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });

//        }


    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(userIdKey, MODE_PRIVATE);
        userId = sharedPreferences.getString("firebaseKey", "");

    }
}