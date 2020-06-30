package com.workspace.adminpanels.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

    public pembayaranAdapter(ArrayList<pembayaranModel> pembayaranList) {
        this.pembayaranList = pembayaranList;
    }

    @NonNull
    @Override
    public pembayaranAdapter.pembayaranHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new pembayaranHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardpembayaran_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull pembayaranAdapter.pembayaranHolder holder, final int position) {

        pembayaranModel pembayaranMod = pembayaranList.get(position);
        holder.IdBayar.setText("#" + pembayaranMod.getId().toString());
        holder.namaPembayar.setText(pembayaranMod.getNamaPenerima());
        holder.nomorPembayar.setText(pembayaranMod.getnomerPenerima());
        holder.jumlahBayar.setText(pembayaranMod.getJumlah());
        holder.totalBayar.setText("Rp " + pembayaranMod.getTotal());
        holder.metodeBayar.setText(pembayaranMod.getMetodeBayar());
        holder.petunjukBayar.setText(pembayaranMod.getPetunjuk());
        holder.alamatBayar.setText(pembayaranMod.getAlamatPenerima());


        boolean isExpanded = pembayaranList.get(position).isExpanded();
        holder.expandLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pembayaranModel mPembayaranMod = pembayaranList.get(position);
                mPembayaranMod.setExpanded(!mPembayaranMod.isExpanded());
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pembayaranList.size();
    }

    public class pembayaranHolder extends RecyclerView.ViewHolder {
        TextView namaPembayar, nomorPembayar, jumlahBayar, metodeBayar, alamatBayar, petunjukBayar, totalBayar, IdBayar;
        LinearLayout expandLayout;
        public pembayaranHolder(@NonNull View itemView) {
            super(itemView);
            namaPembayar = itemView.findViewById(R.id.textNamaPembayaran);
            nomorPembayar = itemView.findViewById(R.id.textNoTelpPembayaran);
            jumlahBayar = itemView.findViewById(R.id.textJumlahPembayaran);
            metodeBayar = itemView.findViewById(R.id.textMetodePembayaran);
            totalBayar = itemView.findViewById(R.id.textTotalPembayaran);
            alamatBayar = itemView.findViewById(R.id.textAlamatPembayaran);
            petunjukBayar = itemView.findViewById(R.id.textPetunjukPembayaran);
            expandLayout = itemView.findViewById(R.id.linear_layout_2);
            IdBayar = itemView.findViewById(R.id.textID);
        }
    }
}
