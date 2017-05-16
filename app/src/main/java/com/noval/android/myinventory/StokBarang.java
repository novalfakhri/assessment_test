package com.noval.android.myinventory;

import java.io.Serializable;

/**
 * Created by Toshiba on 5/16/2017.
 */

public class StokBarang implements Serializable {
    private String tanggal;
    private String nama;
    private String jumlah;
    private String harga;
    private String pemasok;
    private String keterangan;

    public StokBarang(String tanggal, String nama, String jumlah, String harga, String pemasok, String keterangan) {
        this.tanggal = tanggal;
        this.nama = nama;
        this.jumlah = jumlah;
        this.harga = harga;
        this.pemasok = pemasok;
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getPemasok() {
        return pemasok;
    }

    public void setPemasok(String pemasok) {
        this.pemasok = pemasok;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}
