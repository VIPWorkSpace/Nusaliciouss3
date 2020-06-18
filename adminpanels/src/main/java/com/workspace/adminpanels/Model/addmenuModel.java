package com.workspace.adminpanels.Model;

public class addmenuModel {
    String judul;
    String desc;
    Integer harga;
    String kategori;
    String mImageUrl;

    public addmenuModel() {
    }

    public addmenuModel(String judul, String desc, Integer harga, String mImageUrl, String kategori) {
        if (judul.trim().equals("") && desc.trim().equals("") && kategori.trim().equals("")){
            judul = "No Paket";
            desc = "No Desc";
            kategori = "No Kategori";
        }
        this.judul = judul;
        this.desc = desc;
        this.harga = harga;
        this.mImageUrl = mImageUrl;
        this.kategori = kategori;
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
