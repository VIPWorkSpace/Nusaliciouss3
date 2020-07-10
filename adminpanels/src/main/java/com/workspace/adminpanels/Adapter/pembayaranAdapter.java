package com.workspace.adminpanels.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.workspace.adminpanels.Model.pembayaranModel;
import com.workspace.adminpanels.Model.tempPembayaran;
import com.workspace.adminpanels.R;

import java.util.ArrayList;
import java.util.List;

public class pembayaranAdapter extends RecyclerView.Adapter<pembayaranAdapter.pembayaranHolder> implements Filterable {

    private DatabaseReference pembayaranRef;
    private ArrayList<pembayaranModel> pembayaranList;
    private ArrayList<pembayaranModel> listFull;
    private Context context;

    public pembayaranAdapter(ArrayList<pembayaranModel> pembayaranList, Context context) {
        this.pembayaranList = pembayaranList;
        this.context = context;
    }

    @NonNull
    @Override
    public pembayaranAdapter.pembayaranHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardpembayaran_item, parent, false);
        pembayaranHolder mHolder = new pembayaranHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull pembayaranAdapter.pembayaranHolder holder, final int position) {
        final pembayaranModel pembayaranMod = pembayaranList.get(position);

        holder.IdBayar.setText("#" + pembayaranMod.getId());
        holder.namaPembayar.setText(pembayaranMod.getNamaPenerima());
        holder.nomorPembayar.setText(pembayaranMod.getnomerPenerima());
        holder.jumlahBayar.setText(pembayaranMod.getJumlah());
        holder.totalBayar.setText("Rp " + pembayaranMod.getTotal());
        holder.metodeBayar.setText(pembayaranMod.getMetodeBayar());
        holder.petunjukBayar.setText(pembayaranMod.getPetunjuk());
        holder.alamatBayar.setText(pembayaranMod.getAlamatPenerima());
        holder.btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialog = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new ViewHolder(R.layout.dialog_set))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();

                View holderView = (LinearLayout) dialog.getHolderView();
                Button btnSave = holderView.findViewById(R.id.btn_save_datapesanan);
                Button btnDelete = holderView.findViewById(R.id.btn_delete_datapesanan);

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pembayaranRef = FirebaseDatabase.getInstance().getReference("Data").child("Transaksi");
                        pembayaranRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                                    String key = dataSnapshot1.getKey();

                                    Integer idPembayaran = pembayaranMod.getId();
                                    String idPem = String.valueOf(idPembayaran);
                                    String namaPembayar = pembayaranMod.getNamaPenerima();
                                    String nomorPembayar = pembayaranMod.getnomerPenerima();
                                    String jumlah = pembayaranMod.getJumlah();
                                    String totalBayar = pembayaranMod.getTotal();
                                    Integer total = Integer.valueOf(totalBayar);
                                    String metodeBayar = pembayaranMod.getMetodeBayar();
                                    String petunjuk = pembayaranMod.getPetunjuk();
                                    String alamatBayar = pembayaranMod.getAlamatPenerima();
                                    String tanggalBayar = pembayaranMod.getTanggalBayar();

                                    tempPembayaran pemMod = new tempPembayaran(idPembayaran, namaPembayar, nomorPembayar, jumlah, total, metodeBayar, petunjuk, alamatBayar);

                                    FirebaseDatabase.getInstance().getReference("Temp").child("Transaksi")
                                            .child("Data Pembayaran").child(idPem).setValue(pemMod)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    dialog.dismiss();
                                                    Log.i("TAG", "onComplete: ");
                                        }
                                    });
                                    tempPembayaran pemMods = new tempPembayaran(idPembayaran, total, tanggalBayar);
                                    FirebaseDatabase.getInstance().getReference("Data").child("Pendapatan").child(idPem)
                                            .setValue(pemMods).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });
                dialog.show();
            }
        });

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
        TextView namaPembayar, nomorPembayar, jumlahBayar, metodeBayar, alamatBayar, petunjukBayar, totalBayar, IdBayar, btnSelesai;
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
            btnSelesai = itemView.findViewById(R.id.btn_selesai);
        }
    }
}
