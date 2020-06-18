package com.workspace.adminpanels.Model;

public class menuModel {

    String judul;
    String desc;
    Integer harga;
    String kategori;
    String mImageUrl;

    public menuModel() {
    }

    public menuModel(String judul, String desc, Integer harga, String kategori, String mImageUrl) {
        this.judul = judul;
        this.desc = desc;
        this.harga = harga;
        this.kategori = kategori;
        this.mImageUrl = mImageUrl;
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

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
