package com.workspace.adminpanels.Model;

public class addmenuModel {
    String judul;
    String desc;
    Integer harga;
    String kategori;
    String katering;
    String gambar;

    public addmenuModel() {
    }

    public addmenuModel(String judul, String desc, Integer harga, String kategori, String katering, String gambar) {
        this.judul = judul;
        this.desc = desc;
        this.harga = harga;
        this.kategori = kategori;
        this.katering = katering;
        this.gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKatering() {
        return katering;
    }

    public void setKatering(String katering) {
        this.katering = katering;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
