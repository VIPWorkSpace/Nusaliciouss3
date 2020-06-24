package com.workspace.nusali.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.workspace.nusali.Model.ChartModel;
import com.workspace.nusali.Model.PaymentModel;
import com.workspace.nusali.R;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {
    private ArrayList<PaymentModel> paymentList;
    private String userIdKey = "";
    private String userId = "";
    TextView totalTagihan, jumlahPesanan, totalBayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getUsernameLocal();
        totalTagihan = findViewById(R.id.total_tagihan_payment);
        jumlahPesanan = findViewById(R.id.jumlah_pesanan_chart);
        totalBayar = findViewById(R.id.total_bayar_payment);

        getDataPembayaran();


    }

    public void getDataPembayaran(){

    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(userIdKey, MODE_PRIVATE);
        userId = sharedPreferences.getString("firebaseKey", "");

    }
}