package com.workspace.adminpanels.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.workspace.adminpanels.Model.riwayat2Model;
import com.workspace.adminpanels.R;

import java.util.ArrayList;

public class riwayatAdapter extends RecyclerView.Adapter<riwayatAdapter.myViewHolder> {
    ArrayList<riwayat2Model> list;
    Context context;

    public riwayatAdapter(ArrayList<riwayat2Model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public riwayatAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardriwayat_pesanan_item, parent, false);
        myViewHolder myHolder = new myViewHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull riwayatAdapter.myViewHolder holder, final int position) {
        final riwayat2Model riMod = list.get(position);
        holder.idPem.setText("#" + riMod.getXkey());
        holder.noPem.setText(riMod.getId().toString());
        holder.namaPaket.setText(riMod.getJudul());
        holder.statusRiwayat.setText(riMod.getStatus());
        holder.tanggalKirim.setText(riMod.getTanggal());
        holder.waktuKirim.setText(riMod.getWaktu());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView idPem, noPem, namaPaket, statusRiwayat, tanggalKirim, waktuKirim;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            idPem = itemView.findViewById(R.id.text_idpembayaran_riwayat);
            noPem = itemView.findViewById(R.id.text_nopem_riwayat);
            namaPaket = itemView.findViewById(R.id.text_napakt_riwayat);
            statusRiwayat = itemView.findViewById(R.id.text_status_riwayat);
            tanggalKirim = itemView.findViewById(R.id.text_tanggal_riwayat);
            waktuKirim = itemView.findViewById(R.id.text_waktu_riwayat);
        }
    }
}
