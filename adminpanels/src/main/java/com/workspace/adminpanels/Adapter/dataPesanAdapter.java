package com.workspace.adminpanels.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.workspace.adminpanels.Model.callbackidModel;
import com.workspace.adminpanels.Model.dataPesanModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;

public class dataPesanAdapter extends RecyclerView.Adapter<dataPesanAdapter.myHolder> {

    ArrayList<dataPesanModel>modelList;
    ArrayList<callbackidModel>models;

    public dataPesanAdapter(ArrayList<dataPesanModel> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public dataPesanAdapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_data_pesanan_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull dataPesanAdapter.myHolder holder, final int position) {
    dataPesanModel pesanMods = modelList.get(position);
 //   callbackidModel callBack = models.get(position);
    holder.key.setText(pesanMods.getKey());
    holder.noPesan.setText(pesanMods.getId().toString());
    holder.judulPesan.setText(pesanMods.getJudul());
    holder.kateringPesan.setText(pesanMods.getKatering());
    holder.jumlahPesan.setText(pesanMods.getJumlah().toString());
    holder.totalPesan.setText(pesanMods.getTotal().toString());
    holder.tanggalPesan.setText(pesanMods.getTanggal());
    holder.jamPesan.setText(pesanMods.getWaktu());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        TextView key, noPesan, judulPesan, kateringPesan, jumlahPesan, totalPesan, tanggalPesan, jamPesan;
        LinearLayout expand;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            key = itemView.findViewById(R.id.text_key);
            noPesan = itemView.findViewById(R.id.text_id_pesanan);
            judulPesan = itemView.findViewById(R.id.text_judul_pesanan);
            kateringPesan = itemView.findViewById(R.id.text_katering_pesanan);
            jumlahPesan = itemView.findViewById(R.id.text_jumlah_pesanan);
            totalPesan = itemView.findViewById(R.id.text_total_pesanan);
            tanggalPesan = itemView.findViewById(R.id.text_tanggal_pesanan);
            jamPesan = itemView.findViewById(R.id.text_jam_pesanan);

            expand = itemView.findViewById(R.id.expanded_data_pesanan);

        }
    }
}
