package com.workspace.nusali.Model;

import java.text.SimpleDateFormat;

public class PaymentModel {
    Integer idTransaksi;
    Integer jumlah;
    String katering;
    String tanggal;
    Integer total;
    String waktu;
    String dateorder;
    String timeorder;


    public PaymentModel(Integer idTransaksi,  Integer jumlah, String katering, String tanggal, Integer total, String waktu, String dateorder, String timeorder) {
        this.idTransaksi = idTransaksi;
        this.jumlah = jumlah;
        this.katering = katering;
        this.tanggal = tanggal;
        this.total = total;
        this.waktu = waktu;
        this.dateorder = dateorder;
        this.timeorder = timeorder;
    }

    public Integer getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Integer idTransaksi) {
        this.idTransaksi = idTransaksi;
    }



    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public String getKatering() {
        return katering;
    }

    public void setKatering(String katering) {
        this.katering = katering;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getDateorder() {
        return dateorder;
    }

    public void setDateorder(String dateorder) {
        this.dateorder = dateorder;
    }

    public String getTimeorder() {
        return timeorder;
    }

    public void setTimeorder(String timeorder) {
        this.timeorder = timeorder;
    }
}
