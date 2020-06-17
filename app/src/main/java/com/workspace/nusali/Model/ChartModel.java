package com.workspace.nusali.Model;

public class ChartModel {
        String judul;
        String jumlah;
        String katering;
        String tanggal;
        String total;
        String waktu;

    public ChartModel() {
    }

    public ChartModel(String judul, String jumlah, String katering, String tanggal, String total, String waktu) {
        this.judul = judul;
        this.jumlah = jumlah;
        this.katering = katering;
        this.tanggal = tanggal;
        this.total = total;
        this.waktu = waktu;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getKatering() {
        return katering;
    }

    public void setKatering(String katering) {
        this.katering = katering;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
