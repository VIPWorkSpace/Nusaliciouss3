package com.workspace.nusali.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.workspace.nusali.Adapter.ChartAdapter;
import com.workspace.nusali.MainActivity;
import com.workspace.nusali.Model.ChartModel;
import com.workspace.nusali.Model.OrderModel;
import com.workspace.nusali.Model.PaymentModel;
import com.workspace.nusali.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<ChartModel> chartList;
    DatabaseReference referenceOrder, referenceChart, referenceUser;
    private String userIdKey = "";
    private String userId = "";
    DatabaseReference referencePayment;
    Task<Void> referenceRemove;
    TextView totalTagihan, jumlahPesanan, totalBayar, saldoUser;
    String idTransaksi, totalHarga, jumlahItem, namaPenerima, alamatPenerima, nomerPenerima, petunjuk;
    RecyclerView recyclerViewChart;
    ChartAdapter chartAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getUsernameLocal();
        totalTagihan = findViewById(R.id.total_tagihan_payment);
        jumlahPesanan = findViewById(R.id.jumlah_item_payment4);
        totalBayar = findViewById(R.id.total_bayar_payment);
        saldoUser = findViewById(R.id.saldo_anda);
        Bundle bundle = getIntent().getExtras();
        idTransaksi = bundle.getString("idTrans");
//        idbayar.setText(idTransaksi);
        totalHarga = bundle.getString("total");
        jumlahItem = bundle.getString("pesanan");
        namaPenerima = bundle.getString("namaPenerima");
        alamatPenerima = bundle.getString("alamatPenerima");
        nomerPenerima = bundle.getString("nomerPenerima");
        petunjuk = bundle.getString("petunjuk");
        totalTagihan.setText(totalHarga);
        jumlahPesanan.setText(jumlahItem);
        totalBayar.setText(totalHarga);
        getSaldoUser();
//        getDataPembayaran();
        chartList = new ArrayList<>();
        Button btnGpay = findViewById(R.id.btn_g_pay);
        Button mPay = findViewById(R.id.btn_m_pay);
        btnGpay.setOnClickListener(this);

        //set Recycler
        recyclerViewChart = findViewById(R.id.CheckoutRecycler);
        recyclerViewChart.setHasFixedSize(true);
        recyclerViewChart.setLayoutManager(new LinearLayoutManager(this));
        chartList = new ArrayList<>();
       recycler();


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_g_pay:

                goToPesanan();

                break;

            case R.id.btn_m_pay:

                break;
        }

    }

    public void getSaldoUser(){
        referenceUser = FirebaseDatabase.getInstance().getReference("Data").child("User").child(userId).child("pribadi");

        referenceUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                saldoUser.setText(String.format("Rp.%s", dataSnapshot.child("saldo").getValue().toString()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public void recycler(){
        //LOAD RECYCLER KERANJANG
        referenceChart = FirebaseDatabase.getInstance().getReference("Data").child("Keranjang").child(userId);
        referenceChart.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ChartModel chartModel = dataSnapshot1.getValue(ChartModel.class);
                    chartList.add(chartModel);
                    chartAdapter = new ChartAdapter(PaymentActivity.this, chartList);
                    recyclerViewChart.setAdapter(chartAdapter);

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void goToPesanan() {


        referenceOrder = FirebaseDatabase.getInstance().getReference("Data").child("Transaksi").child(userId);
        referenceOrder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String saveCurrentTime, saveCurrentDate;

                Calendar calForDate = Calendar.getInstance();
                final SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                saveCurrentDate = currentDate.format(calForDate.getTime());

                final SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calForDate.getTime());
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ChartModel chartModel = dataSnapshot1.getValue(ChartModel.class);
                    chartList.add(chartModel);
                    for (int i = 0; i < chartList.size(); i++) {
                        Integer idMenu = chartList.get(i).getId();
                        String menuId = String.valueOf(idMenu);
                        String judul = chartList.get(i).getJudul();
                        final Integer jumlah = chartList.get(i).getJumlah();
                        String katering = chartList.get(i).getKatering();
                        String tanggal = chartList.get(i).getTanggal();
                        Integer total = chartList.get(i).getTotal();
                        String waktu = chartList.get(i).getWaktu();

                        OrderModel orderModel = new OrderModel(idMenu, judul, jumlah, katering, tanggal, total, waktu);
                        referenceOrder.child("Pesanan").child(idTransaksi).child(menuId).setValue(orderModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                referencePayment = FirebaseDatabase.getInstance().getReference("Data").child("Transaksi").child(userId).child("Pembayaran");
                                final HashMap<String, Object> paymentMap = new HashMap<>();
                                paymentMap.put("jumlah", jumlahItem);
                                paymentMap.put("total", totalHarga);
                                paymentMap.put("namaPenerima", namaPenerima);
                                paymentMap.put("alamatPenerima", alamatPenerima);
                                paymentMap.put("nomerPenerima", nomerPenerima);
                                paymentMap.put("petunjuk", petunjuk);
                                String metodeBayar = "Gpay";
                                paymentMap.put("metodeBayar", metodeBayar+ " "+saveCurrentDate+ ""+saveCurrentTime);
                                referencePayment.child(idTransaksi).updateChildren(paymentMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        referenceRemove = FirebaseDatabase.getInstance().getReference("Data").child("Keranjang").child(userId).removeValue();
                                        Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            }
                        });



                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(userIdKey, MODE_PRIVATE);
        userId = sharedPreferences.getString("firebaseKey", "");

    }
    public void getDataPembayaran() {

        referencePayment = FirebaseDatabase.getInstance().getReference().child("Transaksi").child(userId).child("Pembayaran").child(idTransaksi);
        referencePayment.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jumlahPesanan.setText(dataSnapshot.child("jumlah").getValue().toString());
                totalTagihan.setText(dataSnapshot.child("total").getValue().toString());
                totalBayar.setText(dataSnapshot.child("total").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}