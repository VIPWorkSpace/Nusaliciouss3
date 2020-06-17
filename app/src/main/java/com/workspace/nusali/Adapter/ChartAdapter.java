package com.workspace.nusali.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.workspace.nusali.Model.ChartModel;
import com.workspace.nusali.R;

import java.util.ArrayList;



public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.MyViewHolder> {
    public Context context;
    private ArrayList<ChartModel> chartList;




    public ChartAdapter(Context c, ArrayList<ChartModel> p) {
        context = c;
        chartList = p;
    }

    @NonNull
    @Override
    public ChartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_row_chart, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ChartAdapter.MyViewHolder holder, int position) {

       final ChartModel chartModel = chartList.get(position);
       holder.judul.setText(chartModel.getJudul());
       holder.jumlah.setText(chartModel.getJumlah().toString());
       holder.harga.setText(chartModel.getTotal().toString());
       holder.tanggal.setText(chartModel.getTanggal());
       holder.waktu.setText(chartModel.getWaktu());

    }

    @Override
    public int getItemCount() {
        return chartList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
            TextView judul, harga, jumlah, tanggal, waktu;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.title_chart);
            harga = itemView.findViewById(R.id.price_chart);
            jumlah = itemView.findViewById(R.id.quantity_chart);
            tanggal = itemView.findViewById(R.id.date_chart);
            waktu = itemView.findViewById(R.id.time_chart);
        }
    }


}
