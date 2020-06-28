package com.workspace.nusali.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.workspace.nusali.Activity.DeliveryLocationActivity;
import com.workspace.nusali.R;

import org.w3c.dom.Text;

import static android.content.Context.MODE_PRIVATE;

public class FragmentAccount extends Fragment implements View.OnClickListener {
    DatabaseReference referenceAccount;
    private String userIdKey = "";
    private String userId = "";
    TextView namaUser, emailUser, teleponUser;
    LinearLayout delivery;
    ImageView imageUser;

    public FragmentAccount() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        namaUser = v.findViewById(R.id.textNama);
        emailUser = v.findViewById(R.id.textEmail);
        teleponUser = v.findViewById(R.id.textTelp);
        imageUser = v.findViewById(R.id.imageUser);
        delivery = v.findViewById(R.id.delivery_location);

        delivery.setOnClickListener(this);
        getUsernameLocal();
        getDataUser();
        return v;
    }

    public void getDataUser() {
        //load data yang ada
        referenceAccount = FirebaseDatabase.getInstance().getReference("Data").child("User").child(userId).child("pribadi");
        referenceAccount.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    namaUser.setText(dataSnapshot.child("name").getValue().toString());
                    emailUser.setText(dataSnapshot.child("email").getValue().toString());
                    teleponUser.setText(dataSnapshot.child("phone").getValue().toString());
                    Picasso.get().load(dataSnapshot.child("url_foto").getValue().toString()).into(imageUser);
                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(userIdKey, MODE_PRIVATE);
        userId = sharedPreferences.getString("firebaseKey", "");

    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.delivery_location:
                    Intent move = new Intent(getActivity(), DeliveryLocationActivity.class);
                    startActivity(move);
                    break;
            }
    }
}