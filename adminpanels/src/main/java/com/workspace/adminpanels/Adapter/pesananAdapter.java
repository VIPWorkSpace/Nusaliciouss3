package com.workspace.adminpanels.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.workspace.adminpanels.Model.pesananModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;

public class pesananAdapter extends RecyclerView.Adapter<pesananAdapter.pesananHolders> {

    ArrayList<pesananModel> pesananList;
    Context context;

    public pesananAdapter(ArrayList<pesananModel> pesananList, Context context) {
        this.context = context;
        this.pesananList = pesananList;
    }

    @NonNull
    @Override
    public pesananAdapter.pesananHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new pesananHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardpesanan_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull pesananAdapter.pesananHolders holder, int position) {
    pesananModel pesananMod = pesananList.get(position);
    holder.textId.setText(pesananMod.getId().toString());
    holder.textJudul.setText(pesananMod.getJudul());
    holder.textJumlah.setText(pesananMod.getJumlah().toString());
    holder.textTotal.setText(pesananMod.getTotal().toString());
    }

    @Override
    public int getItemCount() {
        return pesananList.size();
    }

    public class pesananHolders extends RecyclerView.ViewHolder {
        TextView textId, textJudul, textJumlah, textTotal;
        public pesananHolders(@NonNull View itemView) {
            super(itemView);
            textId = itemView.findViewById(R.id.textIdPesanan);
            textJudul = itemView.findViewById(R.id.textJudulPesanan);
            textJumlah = itemView.findViewById(R.id.textJumlahPesanan);
            textTotal = itemView.findViewById(R.id.textTotalPesanan);
        }
    }
}
