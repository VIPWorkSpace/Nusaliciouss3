package com.workspace.nusali.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.workspace.nusali.Activity.DetailMenuActivity;
import com.workspace.nusali.Activity.Equipment;
import com.workspace.nusali.Activity.ListMenuActivity;
import com.workspace.nusali.Activity.PaymentActivity;
import com.workspace.nusali.R;

import static android.content.Context.MODE_PRIVATE;

public class FragmentHome extends Fragment{
//    BillingProcessor bp;
    DatabaseReference referenceUser;
    String userIdKey = "";
    String userId = "";
    private int[] mImages = new int[] {
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

        getUsernameLocal();

        final TextView nameUser = v.findViewById(R.id.name_user);
        final TextView saldoUser = v.findViewById(R.id.saldo_user);

//        bp = new BillingProcessor(getContext(), "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyEzA2MHjOd3y0ubv7AZf5xZKW9OzszQYDmZbRCFBHV6FQYKkCaMyqP0paT8eck8AJ32BYY8L0jnbIV0DdM3Ejok2nkFRZx7pnNd9v7zwPlRGgN9bFo12GHJ5CTwLRoKz+bjl1tQmdPjbvs9MtzzacQRShN1jZNAt6kBA8OZifHf1Nn+JPZjTOzs1CMlUTSmfruqV6wan8bYNvBC0LMqNqLPjQ/Mve/bpwNnl5/PypjksufxXhUp4vbnVnMZvY8TbfLT8orMXD6j0NUOmYPX4+bNhR0lLF64zLKK4Fy2btYNR/uUfmUA+KtHx1eZEKY9L8XQmIe/zFIVq3iOz5CxbsQIDAQAB", (BillingProcessor.IBillingHandler) getActivity());
//        bp.initialize();

        referenceUser = FirebaseDatabase.getInstance().getReference().child("User").child(userId).child("pribadi");

        referenceUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nameUser.setText(String.format("Hi, %s", dataSnapshot.child("name").getValue()));
                saldoUser.setText(String.format("Rp.%s", dataSnapshot.child("saldo").getValue().toString()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
        for (int i = 0; i < homeGrid.getChildCount(); i++){
            CardView mCardView = (CardView) homeGrid.getChildAt(i);
            final int finall = i;
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finall == 0){
                        Intent intent = new Intent(getContext(), ListMenuActivity.class);
                        startActivity(intent);
                    }
                    else if (finall == 1){
                        Intent intent = new Intent(getContext(), ListMenuActivity.class);
                        startActivity(intent);
                    }
                    else if (finall == 2){
                        Intent intent = new Intent(getContext(), ListMenuActivity.class);
                        startActivity(intent);
                    }
                    else if (finall == 3){
                        Intent intent = new Intent(getContext(), PaymentActivity.class);
                        startActivity(intent);
                    }
                    else if (finall == 4){
                        Intent intent = new Intent(getContext(), ListMenuActivity.class);
                        startActivity(intent);
                    }
                    else if (finall == 5){
                        Intent intent = new Intent(getContext(), ListMenuActivity.class);
                        startActivity(intent);
                    }
                    else if (finall == 6){
                        Intent intent = new Intent(getContext(), ListMenuActivity.class);
                        startActivity(intent);
                    }
                    else if (finall == 7){
                        Intent intent = new Intent(getContext(), Equipment.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
    public void getUsernameLocal() {

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(userIdKey, MODE_PRIVATE);
        userId = sharedPreferences.getString("firebaseKey", "");
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }
//
//        @Override
//    public void onDestroy() {
//        if (bp != null) {
//            bp.release();
//        }
//        super.onDestroy();
//    }


//    @Override
//    public void onProductPurchased(String productId, TransactionDetails details) {
//
//    }
//
//    @Override
//    public void onPurchaseHistoryRestored() {
//
//    }
//
//    @Override
//    public void onBillingError(int errorCode, Throwable error) {
//
//    }
//
//    @Override
//    public void onBillingInitialized() {
//
//    }
}
