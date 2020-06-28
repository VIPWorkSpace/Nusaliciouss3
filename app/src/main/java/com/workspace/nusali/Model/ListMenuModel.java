package com.workspace.nusali.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ListMenuModel implements Parcelable {
    String judul;
    String desc;
    Integer harga;
    String kategori;
    String katering;
    String gambar;
    Integer minimal;

    public ListMenuModel() {
    }

    public ListMenuModel(String judul, String desc, Integer harga, String kategori, String katering, String gambar, Integer minimal) {
        this.judul = judul;
        this.desc = desc;
        this.harga = harga;
        this.kategori = kategori;
        this.katering = katering;
        this.gambar = gambar;
        this.minimal = minimal;
    }

    public String getJudul() {
        return judul;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getHarga() {
        return harga;
    }

    public String getKategori() {
        return kategori;
    }

    public String getKatering() {
        return katering;
    }

    public String getGambar() {
        return gambar;
    }

    public Integer getMinimal() {
        return minimal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.judul);
        dest.writeString(this.desc);
        dest.writeValue(this.harga);
        dest.writeString(this.kategori);
        dest.writeString(this.katering);
        dest.writeString(this.gambar);
        dest.writeValue(this.minimal);

    }

    protected ListMenuModel(Parcel in) {
        this.judul = in.readString();
        this.desc = in.readString();
        this.harga = (Integer) in.readValue(Integer.class.getClassLoader());
        this.kategori = in.readString();
        this.katering = in.readString();
        this.gambar = in.readString();
        this.minimal = (Integer) in.readValue(Integer.class.getClassLoader());

    }

    public static final Parcelable.Creator<ListMenuModel> CREATOR = new Parcelable.Creator<ListMenuModel>() {
        @Override
        public ListMenuModel createFromParcel(Parcel source) {
            return new ListMenuModel(source);
        }

        @Override
        public ListMenuModel[] newArray(int size) {
            return new ListMenuModel[size];
        }
    };
}
