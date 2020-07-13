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
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;

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

public class FragmentHome extends Fragment {
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

        getSaldo();
//        bp = new BillingProcessor(getContext(), "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyEzA2MHjOd3y0ubv7AZf5xZKW9OzszQYDmZbRCFBHV6FQYKkCaMyqP0paT8eck8AJ32BYY8L0jnbIV0DdM3Ejok2nkFRZx7pnNd9v7zwPlRGgN9bFo12GHJ5CTwLRoKz+bjl1tQmdPjbvs9MtzzacQRShN1jZNAt6kBA8OZifHf1Nn+JPZjTOzs1CMlUTSmfruqV6wan8bYNvBC0LMqNqLPjQ/Mve/bpwNnl5/PypjksufxXhUp4vbnVnMZvY8TbfLT8orMXD6j0NUOmYPX4+bNhR0lLF64zLKK4Fy2btYNR/uUfmUA+KtHx1eZEKY9L8XQmIe/zFIVq3iOz5CxbsQIDAQAB", (BillingProcessor.IBillingHandler) getActivity());
//        bp.initialize();


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


}
