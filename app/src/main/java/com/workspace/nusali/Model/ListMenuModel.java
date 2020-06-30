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
    String keterangan;

    public ListMenuModel() {
    }

    public ListMenuModel(String judul, String desc, Integer harga, String kategori, String katering, String gambar, Integer minimal, String keterangan) {
        this.judul = judul;
        this.desc = desc;
        this.harga = harga;
        this.kategori = kategori;
        this.katering = katering;
        this.gambar = gambar;
        this.minimal = minimal;
        this.keterangan = keterangan;
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
        dest.writeValue(this.keterangan);

    }

    protected ListMenuModel(Parcel in) {
        this.judul = in.readString();
        this.desc = in.readString();
        this.harga = (Integer) in.readValue(Integer.class.getClassLoader());
        this.kategori = in.readString();
        this.katering = in.readString();
        this.gambar = in.readString();
        this.minimal = (Integer) in.readValue(Integer.class.getClassLoader());
        this.keterangan = in.readString();

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
