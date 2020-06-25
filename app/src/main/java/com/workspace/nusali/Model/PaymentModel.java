package com.workspace.nusali.Model;



public class PaymentModel {
    Integer idTransaksi;
    Integer jumlah;
    Integer total;
    String metodeBayar;

    public PaymentModel() {
    }

    public PaymentModel(Integer idTransaksi, Integer jumlah, Integer total, String metodeBayar) {
        this.idTransaksi = idTransaksi;
        this.jumlah = jumlah;
        this.total = total;
        this.metodeBayar = metodeBayar;
    }

    public Integer getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Integer idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getMetodeBayar() {
        return metodeBayar;
    }

    public void setMetodeBayar(String metodeBayar) {
        this.metodeBayar = metodeBayar;
    }
}
