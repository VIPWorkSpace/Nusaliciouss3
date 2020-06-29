package com.workspace.adminpanels.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.workspace.adminpanels.Model.pembayaranModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;

public class pembayaranAdapter extends RecyclerView.Adapter<pembayaranAdapter.pembayaranHolder> {

    ArrayList<pembayaranModel> pembayaranList;
    Context context;

    public pembayaranAdapter(ArrayList<pembayaranModel> pembayaranList, Context context) {
        this.pembayaranList = pembayaranList;
        this.context = context;
    }

    @NonNull
    @Override
    public pembayaranAdapter.pembayaranHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new pembayaranHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardpembayaran_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull pembayaranAdapter.pembayaranHolder holder, int position) {
        pembayaranModel pembayaranMod = pembayaranList.get(position);
        holder.namaPembayar.setText(pembayaranMod.getNamaPenerima());
        holder.nomorPembayar.setText(pembayaranMod.getNomorPenerima());
        holder.jumlahBayar.setText(pembayaranMod.getJumlah());
        holder.metodeBayar.setText(pembayaranMod.getMetodeBayar());
    }

    @Override
    public int getItemCount() {
        return pembayaranList.size();
    }

    public class pembayaranHolder extends RecyclerView.ViewHolder {
        TextView namaPembayar, nomorPembayar, jumlahBayar, metodeBayar;
        public pembayaranHolder(@NonNull View itemView) {
            super(itemView);
            namaPembayar = itemView.findViewById(R.id.textNamaPembayaran);
            nomorPembayar = itemView.findViewById(R.id.textNoTelpPembayaran);
            jumlahBayar = itemView.findViewById(R.id.textJumlahPembayaran);
            metodeBayar = itemView.findViewById(R.id.textMetodePembayaran);
        }
    }
}
