package com.workspace.adminpanels.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.workspace.adminpanels.Model.userModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;

public class userData extends RecyclerView.Adapter<userData.MviewHolder> {

    ArrayList<userModel> userModels;

    public userData(ArrayList<userModel> userModels) {
        this.userModels = userModels;
    }

    @NonNull
    @Override
    public userData.MviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MviewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.carduser_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull userData.MviewHolder holder, int position) {

        userModel userMod = userModels.get(position);
        holder.textNamaUser.setText(userMod.getName());
        holder.textEmailUser.setText(userMod.getEmail());
        holder.textPhoneUser.setText(userMod.getPhone());

    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    public class MviewHolder extends RecyclerView.ViewHolder {
        TextView textNamaUser, textEmailUser, textPhoneUser;
        public MviewHolder(@NonNull View itemView) {
            super(itemView);
            textNamaUser = itemView.findViewById(R.id.textNameUser);
            textEmailUser = itemView.findViewById(R.id.textEmailUser);
            textPhoneUser = itemView.findViewById(R.id.textPhoneUser);
        }
    }
}
