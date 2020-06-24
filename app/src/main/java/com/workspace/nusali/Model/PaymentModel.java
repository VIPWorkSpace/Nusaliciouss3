package com.workspace.nusali.Model;



public class PaymentModel {
    Integer idTransaksi;
    String jumlah;
    Integer total;




    public PaymentModel(Integer idTransaksi, String jumlah, Integer total) {
        this.idTransaksi = idTransaksi;
        this.jumlah = jumlah;
        this.total = total;


    }

    public Integer getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Integer idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }




}
