package com.workspace.nusali.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;

import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import com.workspace.nusali.Activity.ListMenuActivity;

import com.workspace.nusali.Model.UserModel;
import com.workspace.nusali.R;

public class FragmentHome extends Fragment  {
//    String userIdKey = "";
//    String userId = "";
    BillingProcessor bp;
    FirebaseAuth firebaseAuth;
    TextView nameUser, saldoUser;
    DatabaseReference referenceUser;
    UserModel userModel;
    String USER = "";
    private int[] mImages = new int[]{
            R.drawable.spanduk1, R.drawable.spanduk2, R.drawable.spanduk3
    };
    GridLayout homeGrid;

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        nameUser = v.findViewById(R.id.name_user);
        saldoUser = v.findViewById(R.id.saldo_user);
        Button btnTopUp = v.findViewById(R.id.btn_top_up);
//        bp = new BillingProcessor(getContext(), "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6XYKgUmqbH7ice5eoYUWL6+cgfSwxpoc6y1xVdsKUnrWWn/rrwggHmiSIqQ+Z4OwnnSDk4Vs6L5PqURejLxyMHG7cOYvnsE4v1Dsk38q8x7o3o867lbRYBWJR3AXgj9u9oZTxZqP8NZwtpEEyMe+nTOLKQWeJVwHyQOaq8Tp9S/RXBJD4J2tplhcFqWrtEkcEFAuLR6m3CoB9BlnHszUc2BEkALFkAj1qK4e6tTlea3ioPFpCylXiV/0UFh+lHU8GJ3Bp65Qx2MJS96oUC4QtBEo3KVyyhu0Gg+DJsPiWYPLIz4qtD86cwl7CszW04JdyE4t6vACB1fXWUkpuZw0CwIDAQAB", );
//        bp.initialize();
        getSaldo();



        CarouselView carouselView = v.findViewById(R.id.carousel);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });
        //Set GridLayout
        homeGrid = v.findViewById(R.id.homeGrid);
        setSingleEvent(homeGrid);
//        btnTopUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bp.purchase(getActivity(), "TopUp");
//            }
//        });
        //Return
        return v;
    }

    //Gridlayout Click Listener
    private void setSingleEvent(GridLayout homeGrid) {
        for (int i = 0; i < homeGrid.getChildCount(); i++) {
            final CardView mCardView = (CardView) homeGrid.getChildAt(i);
            final int finall = i;
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finall == 0) {
                        Intent intent = new Intent(getContext(), ListMenuActivity.class);
                        intent.putExtra("jenis_menu", "Nasi Kotak");
                        startActivity(intent);
                    } else if (finall == 1) {
                        Intent intent = new Intent(getContext(), ListMenuActivity.class);
                        intent.putExtra("jenis_menu", "Prasmanan");
                        startActivity(intent);
                    } else if (finall == 2) {
                       Toast.makeText(getContext(), "Cooming Soon", Toast.LENGTH_SHORT).show();
                       mCardView.setEnabled(false);
                    } else if (finall == 3) {
                        Toast.makeText(getContext(), "Cooming Soon", Toast.LENGTH_SHORT).show();
                        mCardView.setEnabled(false);
                    } else if (finall == 4) {
                        Toast.makeText(getContext(), "Cooming Soon", Toast.LENGTH_SHORT).show();
                        mCardView.setEnabled(false);
                    } else if (finall == 5) {
                        Toast.makeText(getContext(), "Cooming Soon", Toast.LENGTH_SHORT).show();
                        mCardView.setEnabled(false);
                    } else if (finall == 6) {
                        Toast.makeText(getContext(), "Cooming Soon", Toast.LENGTH_SHORT).show();
                        mCardView.setEnabled(false);
                    } else if (finall == 7) {
                        Toast.makeText(getContext(), "Cooming Soon", Toast.LENGTH_SHORT).show();
                        mCardView.setEnabled(false);
                    }
                }
            });
        }
    }

    public void getSaldo() {
        getUserID();
        referenceUser = FirebaseDatabase.getInstance().getReference("Data").child("User").child(USER).child("pribadi");

        referenceUser.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserModel userModel = dataSnapshot.getValue(UserModel.class);
                saldoUser.setText("Rp."+userModel.getSaldo().toString());
                nameUser.setText("Hi, "+userModel.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    public void getUserID(){
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        USER = firebaseUser.getUid();
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data){
//        if(!bp.handleActivityResult(requestCode, resultCode, data)){
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }
//
//
//    @Override
//    public void onDestroy(){
//        if (bp != null){
//            bp.release();
//        }
//        super.onDestroy();
//    }
//
//    @Override
//    public void onProductPurchased(String productId, TransactionDetails details) {
//            Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onPurchaseHistoryRestored() {
//
//    }
//
//    @Override
//    public void onBillingError(int errorCode, Throwable error) {
//        Toast.makeText(getContext(), "Failed Topup", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onBillingInitialized() {
//
//    }
}
