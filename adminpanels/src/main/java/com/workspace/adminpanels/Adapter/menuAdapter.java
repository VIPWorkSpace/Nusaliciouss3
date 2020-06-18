package com.workspace.adminpanels.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.workspace.adminpanels.Model.menuModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;

public class menuAdapter extends RecyclerView.Adapter<menuAdapter.myHolder> {

    ArrayList<menuModel> menulist;

    public menuAdapter(ArrayList<menuModel> menulist) {
        this.menulist = menulist;
    }

    @NonNull
    @Override
    public menuAdapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardmenu_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull menuAdapter.myHolder holder, int position) {
        menuModel menuMod = menulist.get(position);
        Picasso.get().load(menuMod.getmImageUrl())
                .fit().into(holder.picPreview);
        holder.txNama.setText(menuMod.getJudul());
        holder.txDesc.setText(menuMod.getDesc());
        holder.txHarga.setText(menuMod.getHarga().toString());
        holder.txKategori.setText(menuMod.getKategori());
    }

    @Override
    public int getItemCount() {
        return menulist.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        TextView txNama, txDesc, txHarga, txKategori;
        ImageView picPreview;
        Button btnSave, btnEdit;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            txNama = itemView.findViewById(R.id.textNamaMenu);
            txDesc = itemView.findViewById(R.id.textDescMenu);
            txHarga = itemView.findViewById(R.id.textHargaMenu);
            txKategori = itemView.findViewById(R.id.textCat);
            picPreview = itemView.findViewById(R.id.imgMenuItem);
        }
    }
}
