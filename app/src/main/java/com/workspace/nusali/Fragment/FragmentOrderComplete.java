package com.workspace.nusali.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.workspace.nusali.Activity.ListMenuActivity;
import com.workspace.nusali.R;


public class FragmentOrderComplete extends Fragment implements View.OnClickListener {
    Button btnBuy;
    public FragmentOrderComplete() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_order_complete, container, false);
        btnBuy = v.findViewById(R.id.btnBuyComplete);
        btnBuy.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBuyComplete:
                Intent intent = new Intent(getContext(), ListMenuActivity.class);
                intent.putExtra("jenis_menu", "Nasi Kotak");
                startActivity(intent);
                break;
        }
    }
}