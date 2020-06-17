package com.workspace.adminpanels.Model;

public class menuModel {
    String judul;
    String desc;
    Integer harga;
    String gambar;

    public menuModel() {
    }

    public menuModel(String judul, String desc, Integer harga, String gambar) {
        this.judul = judul;
        this.desc = desc;
        this.harga = harga;
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

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
