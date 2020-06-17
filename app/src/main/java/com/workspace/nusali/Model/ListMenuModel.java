package com.workspace.nusali.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ListMenuModel implements Parcelable {
    String judul;
    Integer harga;
    String desc;
    String gambar;
    String katering;

    public ListMenuModel() {
    }

    public ListMenuModel(String judul, Integer harga, String desc, String gambar, String katering) {
        this.judul = judul;
        this.harga = harga;
        this.desc = desc;
        this.gambar = gambar;
        this.katering = katering;
    }

    public String getJudul() {
        return judul;
    }


    public Integer getHarga() {
        return harga;
    }


    public String getDesc() {
        return desc;
    }


    public String getGambar() {
        return gambar;
    }


    public String getKatering() {
        return katering;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.judul);
        dest.writeValue(this.harga);
        dest.writeString(this.desc);
        dest.writeString(this.gambar);
        dest.writeString(this.katering);
    }

    protected ListMenuModel(Parcel in) {
        this.judul = in.readString();
        this.harga = (Integer) in.readValue(Integer.class.getClassLoader());
        this.desc = in.readString();
        this.gambar = in.readString();
        this.katering = in.readString();
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
