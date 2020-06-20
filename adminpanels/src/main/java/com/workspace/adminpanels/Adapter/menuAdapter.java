package com.workspace.adminpanels.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.workspace.adminpanels.MainActivity;
import com.workspace.adminpanels.Model.menuModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;

public class menuAdapter extends RecyclerView.Adapter<menuAdapter.myHolder> {

    Context ctx;
    ArrayList<menuModel> menulist;
    DatabaseReference dRef;

    public menuAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public menuAdapter(ArrayList<menuModel> menulist) {
        this.menulist = menulist;
    }

    @NonNull
    @Override
    public menuAdapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardmenu_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final menuAdapter.myHolder holder, final int position) {
        final menuModel menuMod = menulist.get(position);
        Picasso.get().load(menuMod.getGambar()).noFade().into(holder.picPreview);
        holder.txNama.setText(menuMod.getJudul());
        holder.txDesc.setText(menuMod.getDesc());
        holder.txHarga.setText("Rp " + menuMod.getHarga().toString());
        holder.txKategori.setText("Kategori " + menuMod.getKategori());

        final String currentJudul = menuMod.getJudul();
        dRef = FirebaseDatabase.getInstance().getReference().child("DataMenu");
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Query query = dRef.orderByChild("judul").equalTo(currentJudul);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            ds.getRef().removeValue();
                        }
                        Toast.makeText(view.getContext(), "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent move = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(move);
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    @Override
    public int getItemCount() {
        return menulist.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        TextView txNama, txDesc, txHarga, txKategori;
        ImageView picPreview;
        Button btnEdit, btnDelete;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            txNama = itemView.findViewById(R.id.textNamaMenu);
            txDesc = itemView.findViewById(R.id.textDescMenu);
            txHarga = itemView.findViewById(R.id.textHargaMenu);
            txKategori = itemView.findViewById(R.id.textCat);
            picPreview = itemView.findViewById(R.id.imgMenuItem2);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
