package com.workspace.nusali.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.workspace.nusali.Model.OrderModel;
import com.workspace.nusali.R;

import java.util.ArrayList;

public class OrderPendingAdapter extends RecyclerView.Adapter<OrderPendingAdapter.MyViewHolder> {
    Context context;
    ArrayList<OrderModel> orderList;
    FirebaseAuth firebaseAuth;
    String USER = "";

    public OrderPendingAdapter(Context c, ArrayList<OrderModel> p){
        context = c;
        orderList = p;
    }

    @NonNull
    @Override
    public OrderPendingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_row_order_pending, parent, false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OrderPendingAdapter.MyViewHolder holder, int position) {
        getUserID();
        final  OrderModel orderModel = orderList.get(position);
        holder.idOrder.setText(orderModel.getId().toString());
        holder.titleMenu.setText(orderModel.getJudul());
        holder.jumlahPesan.setText(orderModel.getJumlah().toString()+ "Pax.");
        holder.tanggal.setText(orderModel.getTanggal());
        holder.waktu.setText(orderModel.getWaktu());

        holder.btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
     return  orderList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView idOrder, titleMenu, jumlahPesan, tanggal, waktu, status, harga;
        Button btnSelesai;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idOrder = itemView.findViewById(R.id.nomer_order);
            titleMenu = itemView.findViewById(R.id.title_order);
            jumlahPesan = itemView.findViewById(R.id.quantity_order);
            tanggal = itemView.findViewById(R.id.date_delivery);
            waktu = itemView.findViewById(R.id.time_delivery);
            status = itemView.findViewById(R.id.delivery_order);
            harga = itemView.findViewById(R.id.price_order);
            btnSelesai = itemView.findViewById(R.id.btn_finish);
        }
    }

    public void getUserID(){
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        USER = firebaseUser.getUid();
    }
}
