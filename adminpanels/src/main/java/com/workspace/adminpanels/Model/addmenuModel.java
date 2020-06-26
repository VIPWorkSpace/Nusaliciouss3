package com.workspace.adminpanels.Model;

public class addmenuModel {
    String judul;
    String desc;
    Integer harga;
    String katering;
    String gambar;
    String kategori;

    public addmenuModel(String textPaket, String textDescs, Integer textharga, String kategori, String textKatering, String mImage) {
        this.judul = textPaket;
        this.desc = textDescs;
        this.harga = textharga;
        this.kategori = kategori;
        this.katering = textKatering;
        this.gambar = mImage;
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

    public String getkategori() {
        return kategori;
    }

    public void setkategori(String kategori) {
        this.kategori = kategori;
    }
}
