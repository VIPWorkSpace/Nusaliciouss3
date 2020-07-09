package com.workspace.nusali.Model;

import android.content.Intent;

public class PaymentModel {
    Integer id;
    String namaPenerima;
    String nomerPenerima;
    String alamatPenerima;
    String petunjuk;
    String jumlah;
    String total;
    String metodeBayar;
    String saveCurrentDate;
    String saveCurrentTime;
    boolean expanded;

    public PaymentModel() {
    }



    public PaymentModel(Integer id, String jumlah, String total, String namaPenerima, String alamatPenerima, String nomerPenerima, String petunjuk, String metodeBayar, String saveCurrentDate, String saveCurrentTime) {
        this.id = id;
        this.namaPenerima = namaPenerima;
        this.nomerPenerima = nomerPenerima;
        this.alamatPenerima = alamatPenerima;
        this.petunjuk = petunjuk;
        this.jumlah = jumlah;
        this.total = total;
        this.metodeBayar = metodeBayar;
        this.saveCurrentDate = saveCurrentDate;
        this.saveCurrentTime = saveCurrentTime;
        this.expanded = false;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public String getnomerPenerima() {
        return nomerPenerima;
    }

    public void setnomerPenerima(String nomerPenerima) {
        this.nomerPenerima = nomerPenerima;
    }

    public String getAlamatPenerima() {
        return alamatPenerima;
    }

    public void setAlamatPenerima(String alamatPenerima) {
        this.alamatPenerima = alamatPenerima;
    }

    public String getPetunjuk() {
        return petunjuk;
    }

    public void setPetunjuk(String petunjuk) {
        this.petunjuk = petunjuk;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getMetodeBayar() {
        return metodeBayar;
    }

    public void setMetodeBayar(String metodeBayar) {
        this.metodeBayar = metodeBayar;
    }

    public String getNomerPenerima() {
        return nomerPenerima;
    }

    public void setNomerPenerima(String nomerPenerima) {
        this.nomerPenerima = nomerPenerima;
    }

    public String getSaveCurrentDate() {
        return saveCurrentDate;
    }

    public void setSaveCurrentDate(String saveCurrentDate) {
        this.saveCurrentDate = saveCurrentDate;
    }

    public String getSaveCurrentTime() {
        return saveCurrentTime;
    }

    public void setSaveCurrentTime(String saveCurrentTime) {
        this.saveCurrentTime = saveCurrentTime;
    }
}
