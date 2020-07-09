package com.workspace.adminpanels.Adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.workspace.adminpanels.MainActivity;
import com.workspace.adminpanels.Model.dataPesanModel;
import com.workspace.adminpanels.Model.riwayatModel;
import com.workspace.adminpanels.R;

import java.util.ArrayList;
import java.util.List;

public class dataPesanAdapter extends RecyclerView.Adapter<dataPesanAdapter.myHolder> implements Filterable {
    DatabaseReference riwayatRef;
    String userId = "";
    FirebaseAuth firebaseAuth;
    ArrayList<dataPesanModel> listmod;
    ArrayList<dataPesanModel>modelList;
    ArrayList<dataPesanModel> modelFull;
    Context context;

    public dataPesanAdapter(ArrayList<dataPesanModel> modelList, Context c) {
        this.context = c;
        this.modelList = modelList;
        this.modelFull = new ArrayList<>(modelList);
    }

    public FirebaseAuth getFirebaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        userId = firebaseUser.getUid();
        return firebaseAuth;
    }

    @NonNull
    @Override
    public dataPesanAdapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_data_pesanan_item, parent, false);
        myHolder myHolder = new myHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final dataPesanAdapter.myHolder holder, final int position) {
    final dataPesanModel pesanMods = modelList.get(position);

    holder.key.setText(pesanMods.getKey());
    holder.noPesan.setText(pesanMods.getId().toString());
    holder.judulPesan.setText(pesanMods.getJudul());
    holder.kateringPesan.setText(pesanMods.getKatering());
    holder.jumlahPesan.setText(pesanMods.getJumlah().toString());
    holder.totalPesan.setText(pesanMods.getTotal().toString());
    holder.tanggalPesan.setText(pesanMods.getTanggal());
    holder.jamPesan.setText(pesanMods.getWaktu());
    holder.textStatus.setText(pesanMods.getStatus());

        boolean isExpand = modelList.get(position).isXpand();
        holder.expand.setVisibility(isExpand ? View.VISIBLE : View.GONE);
        holder.imgDown.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dataPesanModel dataMods  = modelList.get(position);
            dataMods.setXpand(!dataMods.isXpand());
            notifyItemChanged(position);
        }
        });

    holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final DialogPlus dialog = DialogPlus.newDialog(context)
                    .setGravity(Gravity.CENTER)
                    .setMargin(50,0,50,0)
                    .setContentHolder(new ViewHolder(R.layout.dialog_set_status))
                    .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                    .create();

            View holderView = (LinearLayout) dialog.getHolderView();
            final Spinner mSpin = holderView.findViewById(R.id.spin_status);
            TextView selectSpin = holderView.findViewById(R.id.select_status);
            Button btnSave = holderView.findViewById(R.id.btn_save_datapesanan);

            //SpinSettings
            List<String> mUpdate = new ArrayList<>();
            mUpdate.add(0, "Choose status");
            mUpdate.add("Menunggu Pembayaran");
            mUpdate.add("Rincian Pesanan Diterima");
            ArrayAdapter<String> updateAdapter;
            updateAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, mUpdate);
            updateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpin.setAdapter(updateAdapter);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    riwayatRef = FirebaseDatabase.getInstance().getReference("Data").child("Transaksi");
                    riwayatRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                                final String keys = dataSnapshot1.getKey();

                                final String key = pesanMods.getKey();
                                Integer id = pesanMods.getId();
                                final String idMenu = String.valueOf(id);
                                String judul = pesanMods.getJudul();
                                Integer total = pesanMods.getTotal();
                                Integer jumlah = pesanMods.getJumlah();
                                String tanggal = pesanMods.getTanggal();
                                String waktu = pesanMods.getWaktu();
                                String status = mSpin.getSelectedItem().toString();

                                riwayatModel riwayatMod = new riwayatModel(key, id, judul, total, jumlah, tanggal, waktu, status);

                                FirebaseDatabase.getInstance().getReference("Data").child("Transaksi").child(keys).child("Riwayat")
                                        .child(key).child(idMenu).setValue(riwayatMod).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        FirebaseDatabase.getInstance().getReference("Data").child("Transaksi").child(keys).child("Pesanan").child(key).child(idMenu).removeValue();
                                        dialog.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, "Failed" + e.toString(), Toast.LENGTH_SHORT).show();
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
        TextView key, noPesan, judulPesan, kateringPesan, jumlahPesan, totalPesan, tanggalPesan, jamPesan, textStatus;
        ImageView imgDown;
        Button btnUpdate;
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
            textStatus = itemView.findViewById(R.id.text_status_pesanan);
            btnUpdate = itemView.findViewById(R.id.btn_update_datapesanan);
            imgDown = itemView.findViewById(R.id.btn_arrow_down);

            expand = itemView.findViewById(R.id.expanded_data_pesanan);
        }
    }
}
