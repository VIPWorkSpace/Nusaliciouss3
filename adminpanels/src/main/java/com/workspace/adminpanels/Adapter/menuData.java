package com.workspace.adminpanels.Adapter;

import android.content.Context;
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

public class menuData extends RecyclerView.Adapter<menuData.menuHolder> {
    ArrayList<menuModel> menuList;

    public menuData(ArrayList<menuModel> menuList) {
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public menuData.menuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new menuHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardmenu_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull menuData.menuHolder holder, int position) {
        menuModel menuMod = menuList.get(position);
        holder.txtNamaMenu.setText(menuMod.getJudul());
        holder.txtDescMenu.setText(menuMod.getDesc());
        holder.txtHargaMenu.setText(menuMod.getHarga().toString());
        Picasso.get().load(menuMod.getGambar()).into(holder.gambarMenu);
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class menuHolder extends RecyclerView.ViewHolder {
        TextView txtNamaMenu, txtDescMenu, txtHargaMenu;
        Button btnEdit, btnDelete;
        ImageView gambarMenu;
        public menuHolder(@NonNull View itemView) {
            super(itemView);
            txtNamaMenu = itemView.findViewById(R.id.textNamaMenu);
            txtDescMenu = itemView.findViewById(R.id.textDescMenu);
            txtHargaMenu = itemView.findViewById(R.id.textHargaMenu);
            gambarMenu = itemView.findViewById(R.id.imgMenuItem);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
