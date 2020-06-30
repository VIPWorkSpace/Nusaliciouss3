package com.workspace.adminpanels.Model;

public class pembayaranModel {
    String namaPenerima;
    String nomerPenerima;
    String alamatPenerima;
    String petunjuk;
    String jumlah;
    String total;
    String metodeBayar;
    boolean expanded;

    public pembayaranModel() {
    }

    public pembayaranModel(String namaPenerima, String nomerPenerima, String alamatPenerima, String petunjuk, String jumlah, String total, String metodeBayar) {
        this.namaPenerima = namaPenerima;
        this.nomerPenerima = nomerPenerima;
        this.alamatPenerima = alamatPenerima;
        this.petunjuk = petunjuk;
        this.jumlah = jumlah;
        this.total = total;
        this.metodeBayar = metodeBayar;
        this.expanded = false;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public String getnomerPenerima() {
        return nomerPenerima;
    }

    public void setnomerPenerima(String nomerPenerima) {
        this.nomerPenerima = nomerPenerima;
    }

    public String getAlamatPenerima() {
        return alamatPenerima;
    }

    public void setAlamatPenerima(String alamatPenerima) {
        this.alamatPenerima = alamatPenerima;
    }

    public String getPetunjuk() {
        return petunjuk;
    }

    public void setPetunjuk(String petunjuk) {
        this.petunjuk = petunjuk;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getMetodeBayar() {
        return metodeBayar;
    }

    public void setMetodeBayar(String metodeBayar) {
        this.metodeBayar = metodeBayar;
    }
}
