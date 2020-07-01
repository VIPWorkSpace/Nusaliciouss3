package com.workspace.adminpanels.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.workspace.adminpanels.Activity.DataPesanan;
import com.workspace.adminpanels.Model.callbackidModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;

public class pesananAdapter extends RecyclerView.Adapter<pesananAdapter.pesananHolders> {

    ArrayList<callbackidModel> callList;
    Context ctx;

    public pesananAdapter(ArrayList<callbackidModel> callList, Context ctx) {
        this.callList = callList;
        this.ctx = ctx;
    }

    public pesananAdapter(ArrayList<callbackidModel> callList) {
        this.callList = callList;
    }

    @NonNull
    @Override
    public pesananAdapter.pesananHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new pesananHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardpesanan_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final pesananAdapter.pesananHolders holder, int position) {
        final callbackidModel callMod = callList.get(position);
        holder.NoUnix.setText(callMod.getKey());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent(view.getContext(), DataPesanan.class);
                view.getContext().startActivity(send);
            }
        });

    }

    @Override
    public int getItemCount() {
        return callList.size();
    }

    public class pesananHolders extends RecyclerView.ViewHolder {
        TextView NoUnix;
        public pesananHolders(@NonNull View itemView) {
            super(itemView);
            NoUnix = itemView.findViewById(R.id.noUnix);
        }
    }
}
