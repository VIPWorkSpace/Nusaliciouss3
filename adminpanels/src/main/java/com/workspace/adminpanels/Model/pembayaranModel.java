package com.workspace.adminpanels.Model;

public class pembayaranModel {
    String namaPenerima;
    String nomorPenerima;
    String alamatPenerima;
    String petunjuk;
    String jumlah;
    String total;
    String metodeBayar;

    public pembayaranModel() {
    }

    public pembayaranModel(String namaPenerima, String nomorPenerima, String alamatPenerima, String petunjuk, String jumlah, String total, String metodeBayar) {
        this.namaPenerima = namaPenerima;
        this.nomorPenerima = nomorPenerima;
        this.alamatPenerima = alamatPenerima;
        this.petunjuk = petunjuk;
        this.jumlah = jumlah;
        this.total = total;
        this.metodeBayar = metodeBayar;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public String getNomorPenerima() {
        return nomorPenerima;
    }

    public void setNomorPenerima(String nomorPenerima) {
        this.nomorPenerima = nomorPenerima;
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
