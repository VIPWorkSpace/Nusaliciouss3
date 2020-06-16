package com.workspace.nusali.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import com.workspace.nusali.Fragment.FragmentDatePicker;
import com.workspace.nusali.Fragment.FragmentDialogAlert;
import com.workspace.nusali.Model.ListMenuModel;
import com.workspace.nusali.R;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class DetailMenuActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    String userIdKey = "";
    String userId = "";
    CardView cardTanggal;
    TextView judulHalaman, namaKatering, jenisMenu, judulMenu, descMenu, hargaMenu, totalHarga;
    EditText tanggal, waktu, jumlahPesanan;
    ImageView fotoMenu, btnMinus, btnPlus;
    Button btnKeranjang;
    Integer jumlahBeli = 1;
    Integer total = 0;
    Integer belanjaID = new Random().nextInt();
    String idMenu = belanjaID.toString();
    private DatabaseReference referenceChart;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);
        getUsernameLocal();
        //TextView
        judulHalaman = findViewById(R.id.tv_judul_menu);
        namaKatering = findViewById(R.id.nama_katering);
        jenisMenu = findViewById(R.id.jenis_menu);
        judulMenu = findViewById(R.id.judul_detail_menu);
        descMenu = findViewById(R.id.desc_detail_menu);
        hargaMenu = findViewById(R.id.harga_detail_menu);
        totalHarga = findViewById(R.id.total_price);
        //Edit Text
        tanggal = findViewById(R.id.tv_tanggal);
        waktu = findViewById(R.id.tv_time);
        jumlahPesanan = findViewById(R.id.jumlah_pesanan);
        //ImageView
        fotoMenu = findViewById(R.id.image_detail_menu);
        btnMinus = findViewById(R.id.btn_minus);
        btnPlus = findViewById(R.id.btn_plus);
        //Button
        btnKeranjang = findViewById(R.id.btn_chart);
        //CardView
        cardTanggal = findViewById(R.id.card_tanggal);


        //PARCELABLE
        Intent intent = getIntent();
        ListMenuModel listMenuModel = intent.getParcelableExtra("judul");
        //Setting judul
        String judul = Objects.requireNonNull(listMenuModel).getJudul();
        judulMenu.setText(judul);
        //Setting nama katering
        String katering = listMenuModel.getKatering();
        namaKatering.setText(katering);
        //setting harga
        String harga = listMenuModel.getHarga().toString();
        final int hargaDetailMenu = Integer.parseInt(harga);
        hargaMenu.setText("Rp." +Integer.toString(hargaDetailMenu)+ ",-");
        //setting total harga
        totalHarga.setText("Rp."+Integer.toString(hargaDetailMenu));
        //setting desc
        String desc = listMenuModel.getDesc();
        descMenu.setText(desc);
        //setting fotoMenu
        String gambar = listMenuModel.getGambar();
        Picasso.get().load(gambar).into(fotoMenu);

        //Setting Jumlah Pesanan

        jumlahPesanan.setText(jumlahBeli.toString());

        //Setting tanggal
       cardTanggal.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DialogFragment datePicker = new FragmentDatePicker();
               datePicker.show(getSupportFragmentManager(),"date picker");
           }
       });

        //Setting button tambah pesanan
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahBeli += 1;
                jumlahPesanan.setText(jumlahBeli.toString());
                if (jumlahBeli > 1) {
                    btnMinus.setEnabled(true);
                }

                total = jumlahBeli * hargaDetailMenu;
                totalHarga.setText("Rp."+Integer.toString(total));

            }
        });

        //Setting button minus pesanan
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahBeli -= 1;
                jumlahPesanan.setText(jumlahBeli.toString());
                if (jumlahBeli < 2)
                    btnMinus.setEnabled(false);
                total = jumlahBeli * hargaDetailMenu;
                totalHarga.setText("Rp."+Integer.toString(total));
            }

        });


        //Setting button masuk keranjang dengan mangambil uid User dan memberikan uid item keranjang
        btnKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCartList();
            }
        });


    }

    private void addToCartList() {
        String saveCurrentTime, saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Keranjang");

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("id", idMenu);
        cartMap.put("judul", judulMenu.getText().toString());
        cartMap.put("jumlah", jumlahPesanan.getText().toString());
        cartMap.put("total", totalHarga.getText().toString());
        cartMap.put("tanggal", tanggal.getText().toString());
        cartMap.put("waktu", waktu.getText().toString());
        cartMap.put("date", saveCurrentDate);
        cartMap.put("time", saveCurrentTime);

        cartListRef.child(userId).child(idMenu).updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                openDialog();
            }
        });

    }

    public void openDialog(){
        FragmentDialogAlert fragmentDialogAlert = new FragmentDialogAlert();
        fragmentDialogAlert.show(getSupportFragmentManager(), "Success");
    }


    public void getUsernameLocal() {

        SharedPreferences sharedPreferences = getSharedPreferences(userIdKey, MODE_PRIVATE);
        userId = sharedPreferences.getString("firebaseKey", "");

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        tanggal.setText(currentDateString);
    }
}