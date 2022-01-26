/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemcatatpoinkeaktifan;

/**
 *
 * @author acer
 */
public class Prestasi {
    private int IDPrestasi;
    private String JenisPrestasi;
    private String TingkatPrestasi;
    private String Keterangan;
    private String Tanggal;
    private int Poin;
    private String Status;

    public Prestasi(int IDPrestasi, String JenisPrestasi, String TingkatPrestasi, String Keterangan, String Tanggal, int Poin, String Status) {
        this.IDPrestasi = IDPrestasi;
        this.JenisPrestasi = JenisPrestasi;
        this.TingkatPrestasi = TingkatPrestasi;
        this.Keterangan = Keterangan;
        this.Tanggal = Tanggal;
        this.Poin = Poin;
        this.Status = Status;
    }

    Prestasi(int aInt, String string, String string0, String string1, String string2, int aInt0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIDPrestasi() {
        return IDPrestasi;
    }

    public String getJenisPrestasi() {
        return JenisPrestasi;
    }

    public String getTingkatPrestasi() {
        return TingkatPrestasi;
    }

    public String getKeterangan() {
        return Keterangan;
    }
    
    public String getTanggal() {
        return Tanggal;
    }
    
    public int getPoin() {
        return Poin;
    }
    
    public String getStatus() {
        return Status;
    }

    
         
}
