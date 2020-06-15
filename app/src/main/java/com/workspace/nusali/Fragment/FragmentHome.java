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
import com.workspace.nusali.R;

import static android.content.Context.MODE_PRIVATE;

public class FragmentHome extends Fragment {

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


        referenceUser = FirebaseDatabase.getInstance().getReference().child("User").child(userId);

        referenceUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nameUser.setText(String.format("Hi, %s", dataSnapshot.child("name").getValue().toString()));
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
                        Intent intent = new Intent(getContext(), DetailMenuActivity.class);
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
}