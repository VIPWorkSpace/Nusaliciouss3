package com.workspace.adminpanels.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.workspace.adminpanels.Model.pembayaranModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;
import java.util.List;

public class pembayaranAdapter extends RecyclerView.Adapter<pembayaranAdapter.pembayaranHolder> implements Filterable {

    ArrayList<pembayaranModel> pembayaranList;
    ArrayList<pembayaranModel> listFull;
    Context context;
//
//    public pembayaranAdapter(ArrayList<pembayaranModel> pembayaranList, Context context) {
//        this.pembayaranList = pembayaranList;
//        this.context = context;
//    }

    public pembayaranAdapter(ArrayList<pembayaranModel> pembayaranList) {
        this.pembayaranList = pembayaranList;
        this.listFull = new ArrayList<>(pembayaranList);
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

    @Override
    public Filter getFilter() {
        return filters;
    }
    private Filter filters = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<pembayaranModel> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() ==0){
                filteredList.addAll(listFull);
            }else {
                String filterPatern = charSequence.toString().toLowerCase().trim();
                for (pembayaranModel item : listFull){
                    if (item.getId().toString().contains(filterPatern) || item.getNamaPenerima().toLowerCase().contains(filterPatern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            pembayaranList.clear();
            pembayaranList.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

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
