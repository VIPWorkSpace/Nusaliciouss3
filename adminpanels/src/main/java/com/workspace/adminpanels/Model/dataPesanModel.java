package com.workspace.adminpanels.Model;

public class dataPesanModel {

    public String key;
    public Integer id;
    public String judul;
    public String katering;
    public Integer jumlah;
    public Integer total;
    public String tanggal;
    public String waktu;
    boolean xpand;

    public dataPesanModel() {
    }

    public dataPesanModel(String key, Integer id, String judul, String katering, Integer jumlah, Integer total, String tanggal, String waktu) {
        this.key = key;
        this.id = id;
        this.judul = judul;
        this.katering = katering;
        this.jumlah = jumlah;
        this.total = total;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.xpand = false;
    }

    public boolean isXpand() {
        return xpand;
    }

    public void setXpand(boolean xpand) {
        this.xpand = xpand;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKatering() {
        return katering;
    }

    public void setKatering(String katering) {
        this.katering = katering;
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

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
