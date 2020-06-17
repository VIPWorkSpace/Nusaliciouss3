package com.workspace.nusali.Model;

public class ChartModel {
    String id;
    String judul;
    String jumlah;
    String katering;
    String tanggal;
    Integer total;
    String waktu;
    String dateorder;
    String timeorder;

    public ChartModel() {
    }

    public ChartModel(String id, String judul, String jumlah, String katering, String tanggal, Integer total, String waktu, String dateorder, String timeorder) {
        this.id = id;
        this.judul = judul;
        this.jumlah = jumlah;
        this.katering = katering;
        this.tanggal = tanggal;
        this.total = total;
        this.waktu = waktu;
        this.dateorder = dateorder;
        this.timeorder = timeorder;
    }

    public String getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getKatering() {
        return katering;
    }

    public String getTanggal() {
        return tanggal;
    }

    public Integer getTotal() {
        return total;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getDateorder() {
        return dateorder;
    }

    public String getTimeorder() {
        return timeorder;
    }
}
