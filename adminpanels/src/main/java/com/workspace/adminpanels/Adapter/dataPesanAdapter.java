package com.workspace.adminpanels.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.workspace.adminpanels.Model.dataPesanModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;
import java.util.List;

public class dataPesanAdapter extends RecyclerView.Adapter<dataPesanAdapter.myHolder> implements Filterable {

    ArrayList<dataPesanModel>modelList;
    ArrayList<dataPesanModel> modelFull;

    public dataPesanAdapter(ArrayList<dataPesanModel> modelList) {
        this.modelList = modelList;
        this.modelFull = new ArrayList<>(modelList);
    }

    @NonNull
    @Override
    public dataPesanAdapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_data_pesanan_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull dataPesanAdapter.myHolder holder, final int position) {
    dataPesanModel pesanMods = modelList.get(position);

    holder.key.setText(pesanMods.getKey());
    holder.noPesan.setText(pesanMods.getId().toString());
    holder.judulPesan.setText(pesanMods.getJudul());
    holder.kateringPesan.setText(pesanMods.getKatering());
    holder.jumlahPesan.setText(pesanMods.getJumlah().toString());
    holder.totalPesan.setText(pesanMods.getTotal().toString());
    holder.tanggalPesan.setText(pesanMods.getTanggal());
    holder.jamPesan.setText(pesanMods.getWaktu());

    boolean isExpand = modelList.get(position).isXpand();
    holder.expand.setVisibility(isExpand ? View.VISIBLE : View.GONE);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dataPesanModel dataMods  = modelList.get(position);
            dataMods.setXpand(!dataMods.isXpand());
            notifyItemChanged(position);
        }
    });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<dataPesanModel> filterList = new ArrayList<>();
            if (charSequence == null || charSequence.length() ==0 ){
                filterList.addAll(modelFull);
            }else {
                String filterPatern = charSequence.toString().toLowerCase().trim();
                for (dataPesanModel item : modelFull){
                    if (item.getId().toString().contains(filterPatern) || item.getKey().toString().contains(filterPatern)){
                        filterList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            modelList.clear();
            modelList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

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
